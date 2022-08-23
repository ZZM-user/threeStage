<template>
  <div id="main">
    <el-form v-admin :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家">
        <el-select v-model="queryFrom.eid" :loading="loading" clearable filterable
                   placeholder="请选择商家" size="small"
                   @visible-change="fetchOptions()"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right; font-size: 13px; font-weight: 600">
                <span v-if="item.status===0" style="color: #27ae60">正常</span>
                <span v-if="item.status===1" style="color: #a4b0be">停用</span>
                <span v-if="item.status===2" style="color: #EBB563">未审核</span>
            </span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="分类">
        <el-input v-model="queryFrom.name" clearable placeholder="分类名" size="small"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>
    <!--    工具栏-->
    <el-row>
      <el-col>
        <el-button v-enterprise icon="el-icon-plus" size="small" type="success" @click="openDialog(1)">新增</el-button>
        <el-button v-enterprise :disabled="ableEdit" icon="el-icon-edit" size="small" type="warning"
                   @click="openDialog(2)"
        >修改
        </el-button>
        <el-button v-enterprise :disabled="ableDelete" icon="el-icon-delete" size="small" type="danger"
                   @click="submitDelete"
        >
          删除
        </el-button>

        <el-button icon="el-icon-share" size="small" type="primary" @click="">
          导出
        </el-button>
      </el-col>
    </el-row>
    <!--    dialog弹框-->
    <el-dialog :closed="closeDialog" :title="dialogTitle" :visible.sync="dialogVisible" width="62%">
      <el-form ref="dialogForm" :model="dialogForm" :rules="rules" class="demo-ruleForm" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类别名称" prop="name">
              <el-input v-model="dialogForm.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col v-admin :span="12">
            <el-form-item label="所属商家" prop="enterprise_id">
              <el-select v-model="dialogForm.enterprise_id" :loading="loading" clearable
                         filterable placeholder="请选择商家"
                         @visible-change="fetchOptions(true)"
              >
                <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                  <span style="float: left">{{ item.name }}</span>
                  <span style="float: right; font-size: 13px; font-weight: 600">
                    <span v-if="item.status===0" style="color: #27ae60">正常</span>
                    <span v-if="item.status===1" style="color: #a4b0be">停用</span>
                    <span v-if="item.status===2" style="color: #EBB563">未审核</span>
                  </span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="口味图片" prop="picture">
              <el-input v-model="dialogForm.picture" placeholder="请输入口味图片地址" @change="setAlbumOfAvatar"/>
              <el-upload
                :before-upload="beforeAvatarUpload"
                :drag="true"
                :on-success="handleAvatarSuccess"
                :show-file-list="false"
                action="http://localhost:8080/img/upload"
                class="avatar-uploader"
              >
                <el-image
                  v-if="avatar"
                  :src="avatar"
                  fit="scale-down"
                  style="width: 180px; height: 180px"
                ></el-image>
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :push="12" :span="12">
            <el-form-item>
              <el-button type="primary" @click="submitForm()">确定</el-button>
              <el-button @click="closeDialog">关闭</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
    <!--    信息展示-->
    <el-table :data="totalData.records" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="30"/>
      <el-table-column label="编号" prop="id" width="50"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" :show-tooltip-when-overflow="true" label="所属商户"
                       prop="enterprise.name"
                       width="100"
      ></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="名称" prop="name" width="180"></el-table-column>
      <el-table-column label="图片" prop="picture" width="150">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.picture"
            style="width: 100px;"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="create_time"></el-table-column>
      <el-table-column label="创建人" prop="create_by"></el-table-column>
      <el-table-column label="修改时间" prop="update_time"></el-table-column>
      <el-table-column label="修改人" prop="update_by"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link icon="el-icon-view" type="primary" @click="openDialog(2,scope.row)">详情</el-link>
          <el-link v-enterprise icon="el-icon-edit" type="primary" @click="openDialog(2,scope.row)">编辑</el-link>
          |
          <el-link v-enterprise icon="el-icon-delete" type="danger" @click="submitDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :page-sizes="[10, 20, 30, 40,50]"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentPageChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import PageMixin from '@/mixin/PageMixin'
import {
  addCategoryData,
  delCategoryData,
  editCategoryData,
  fetchCategory,
  fetchCategoryData,
  findCategoryData
} from '@/api/category'
import { findEnterprisesData } from '@/api/enterprise'

export default {
  mixins: [PageMixin],
  data() {
    return {
      // 搜索列表相关
      loading: false,
      options: [],
      // 图片暂存
      avatar: '',
      rules: {
        enterprise_id: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ],
        picture: [
          { required: true, message: '请上传口味图片', trigger: 'blur' }
        ]
      }
    }
  }, methods: {
    fetchDataHook() {
      if (this.$store.getters.loginType !== 1) {
        return fetchCategory
      }
      return fetchCategoryData
    },
    addDataHooK() {
      return addCategoryData
    },
    // 编辑框打开之前
    beforeEditDataHook(row) {
      const id = row ? row.id : this.ids[0]
      findCategoryData(id).then(resp => {
        this.dialogForm = resp.data
        this.avatar = this.dialogForm.picture
      }).catch(err => {
          console.log(err)
          return 0
        }
      )
    },
    editDataHook() {
      return editCategoryData
    },
    delDataHook() {
      return delCategoryData
    },
    // 搜索商家
    fetchOptions() {
      this.loading = true
      findEnterprisesData({ enterpriseName: this.dialogForm.enterprise_id }).then(response => {
        if (response.code === 0) {
          this.loading = false
          this.options = response.data
        } else {
          this.options = []
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 当手动键入封面图片时
    setAlbumOfAvatar() {
      this.avatar = this.dialogForm.picture
    },
    // 上传头像成功后
    handleAvatarSuccess(res, file) {
      this.dialogForm.picture = 'http://localhost:8080/' + res.data.fileUrl
      this.avatar = this.dialogForm.picture
    }

  }
}
</script>

<style scoped>
#main {
  padding: 1%;
}

.el-image {
  border-radius: 12%;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  margin: 0 auto;
  border: 1px solid #f1f2f6;
}

</style>
