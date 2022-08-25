<template>
  <div id="main">
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item v-admin label="商家ID">
        <el-input v-model="queryFrom.eid" clearable placeholder="商家ID" size="small"></el-input>
      </el-form-item>
      <el-form-item label="商品名">
        <el-input v-model="queryFrom.name" clearable placeholder="商品名" size="small"></el-input>
      </el-form-item>
      <el-form-item label="是否上架">
        <el-select v-model="queryFrom.isgrounding" clearable placeholder="是否上架" size="small">
          <el-option
            v-for="item in merchandiseStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="small" type="primary" @click="fetchData">查询</el-button>
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
            <el-form-item label="商品名称" prop="name">
              <el-input v-model="dialogForm.name" placeholder="请输入商品名称"/>
            </el-form-item>
          </el-col>
          <el-col v-admin :span="12">
            <el-form-item label="所属商家" prop="b_id">
              <el-select v-model="dialogForm.b_id" :loading="loading" clearable
                         filterable placeholder="请选择商家"
                         @visible-change="fetchOptions(true)"
              >
                <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品价格" prop="price">
              <el-input v-model="dialogForm.price" placeholder="请输入商品价格"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品分类" prop="m_id">
              <el-select v-model="dialogForm.m_id" :loading="loading" clearable
                         filterable placeholder="请选择分类"
                         @visible-change="fetchCategory(true)"
              >
                <el-option
                  v-for="item in categorys"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品图片" prop="picture">
              <el-input v-model="dialogForm.picture" placeholder="请输入商品图片地址" @change="setAlbumOfAvatar"/>
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
          <el-col :span="24">
            <el-form-item label="状态" prop="isgrounding" width="80">
              <el-radio v-model="dialogForm.isgrounding" :label="0">未上架</el-radio>
              <el-radio v-model="dialogForm.isgrounding" :label="1">已上架</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="商品描述" prop="description">
              <el-input
                v-model="dialogForm.description"
                :rows="2"
                maxlength="100"
                minlength="10"
                placeholder="请输入商品描述信息"
                show-word-limit
                type="textarea"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="dialogType!==0">
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
      <el-table-column type="selection" width="55"/>
      <el-table-column align="center" label="编号" prop="id" width="80"/>
      <el-table-column :show-overflow-tooltip="true" label="名称" prop="name" width="120"/>
      <el-table-column label="图片" prop="picture" width="100">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.picture"
            fit="scale-down"
            style="height: 100px"
          ></el-image>
        </template>

      </el-table-column>
      <el-table-column label="价格" prop="price" width="80">
        <template slot-scope="scope">
          ￥{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="描述" prop="description" width="100"/>
      <el-table-column label="状态" prop="isgrounding" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isgrounding===0" effect="dark" type="danger">未上架</el-tag>
          <el-tag v-if="scope.row.isgrounding===1" effect="dark" type="success">已上架</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="所属商家" prop="enterprise.name" width="60"/>
      <el-table-column :show-tooltip-when-overflow="true" label="所属分类" prop="categoryClass.name" width="60"/>
      <el-table-column label="创建时间" prop="create_time" width="150"/>
      <el-table-column label="创建人" prop="create_by" width="80"/>
      <el-table-column label="修改时间" prop="update_time" width="150"/>
      <el-table-column label="修改人" prop="update_by" width="80"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link v-if="scope.row.isgrounding===0" v-enterprise icon="el-icon-check" type="success"
                   @click="changeState(scope.row)"
          >上架
          </el-link>
          <el-link v-else v-enterprise icon="el-icon-check" type="warning" @click="changeState(scope.row)">下架
          </el-link>
          <el-link icon="el-icon-view" type="primary" @click="openDialog(0,scope.row)">详情</el-link>
          <el-link v-enterprise icon="el-icon-edit" type="primary" @click="openDialog(2,scope.row)">编辑</el-link>
          <el-link v-enterprise icon="el-icon-delete" type="danger" @click="submitDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="queryFrom.page"
      :page-size="queryFrom.size"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentPageChange"
    >
    </el-pagination>

  </div>
</template>

<script>
import {
  addMerchandiseData,
  changeMerchandiseState,
  delMerchandiseData,
  editMerchandiseData,
  fetchMerchandise,
  fetchMerchandiseData,
  findMerchandiseData,
  MerchandiseStatus
} from '@/api/merchandise'
import PageMixin from '@/mixin/PageMixin'
import { findEnterprisesData } from '@/api/enterprise'
import { findCategorysData } from '@/api/category'

export default {
  mixins: [PageMixin],
  data() {
    let validatePrice = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入价格'))
      } else if (value < 0) {
        callback(new Error('价格不能为负数！'))
      } else {
        callback()
      }
    }
    return {
      // 搜索列表相关
      loading: false,
      options: [],
      categorys: [],
      // 图片暂存
      avatar: '',
      merchandiseStatus: MerchandiseStatus(),
      rules: {
        b_id: [
          { required: true, message: '所属商家不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        picture: [
          { required: true, message: '请上传商品图片', trigger: 'blur' }
        ],
        price: [
          { validator: validatePrice, trigger: 'blur' }
        ],
        m_id: [
          { required: true, message: '分类不能为空', trigger: 'blur' }
        ],
        isgrounding: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '描述不能为空', trigger: 'blur' },
          { min: 5, max: 100, message: '描述需要在5-100字之间', trigger: 'blur' }
        ]

      }
    }
  }, methods: {
    fetchDataHook() {
      if (this.$store.getters.loginType !== 1) {
        return fetchMerchandise
      }
      return fetchMerchandiseData
    },
    addDataHooK() {
      return addMerchandiseData
    },
    // 编辑框打开之前
    beforeEditDataHook(row) {
      const id = row ? row.id : this.ids[0]
      findMerchandiseData(id).then(resp => {
        this.dialogForm = resp.data
        this.avatar = this.dialogForm.picture
      }).catch(err => {
          console.log(err)
          return 0
        }
      )
    },
    editDataHook() {
      return editMerchandiseData
    },
    delDataHook() {
      return delMerchandiseData
    },
    // 搜索商家
    fetchOptions(query) {
      this.loading = true
      findEnterprisesData({ enterpriseName: this.dialogForm.b_id }).then(response => {
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
    // 搜索分类
    fetchCategory(query) {
      this.loading = true
      findCategorysData({ categoryName: this.dialogForm.m_id }).then(response => {
        if (response.code === 0) {
          this.loading = false
          this.categorys = response.data
        } else {
          this.categorys = []
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
    },
    changeState(row) {
      changeMerchandiseState({ id: row.id }).then(resp => {
        const { code, message, data } = resp
        if (code === 0) {
          this.$message({
            message: message,
            type: 'success'
          })
          this.fetchData()
        } else {
          this.$message({
            message: message || '操作失败！',
            type: 'danger'
          })
        }
      })
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
