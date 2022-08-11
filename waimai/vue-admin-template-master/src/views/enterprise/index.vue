<template>
  <div id="main">
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline" size="small">
      <el-form-item label="商家名称">
        <el-input v-model="queryFrom.name" clearable placeholder="商家名称" size="small"></el-input>
      </el-form-item>
      <el-form-item label="商家电话">
        <el-input v-model="queryFrom.phone" clearable placeholder="商家电话" size="small"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryFrom.status" clearable placeholder="商家状态" size="small">
          <el-option
            v-for="item in enterpriseStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="createDate"
          end-placeholder="结束日期"
          range-separator="至"
          size="small"
          start-placeholder="开始日期"
          type="daterange"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
      <!--    工具栏-->
      <el-row>
        <el-col>
          <el-button icon="el-icon-plus" size="small" type="success" @click="openDialog(1)">新增</el-button>
          <el-button :disabled="ableEdit" icon="el-icon-edit" size="small" type="warning" @click="openDialog(2)">修改
          </el-button>
          <el-button :disabled="ableDelete" icon="el-icon-delete" size="small" type="danger" @click="submitDelete">
            删除
          </el-button>
        </el-col>
      </el-row>
      <!--    dialog弹框-->
      <el-dialog :closed="closeDialog" :title="dialogTitle" :visible.sync="dialogVisible" width="62%">
        <el-form ref="dialogForm" :model="dialogForm" :rules="rules" class="demo-ruleForm" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="商家名称" prop="name">
                <el-input v-model="dialogForm.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="dialogForm.phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!--  不允许通过编辑来修改密码  -->
          <el-row v-if="dialogType===1">
            <el-col :span="12">
              <el-form-item label="登录密码" prop="login_pwd">
                <el-input v-model="dialogForm.login_pwd" type="password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="重复密码" prop="login_pwd_again">
                <el-input v-model="dialogForm.login_pwd_again" type="password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="商家图片" prop="album">
                <el-input v-model="dialogForm.album" placeholder="请输入商家封面图片地址" @change="setAlbumOfAvatar"/>
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
            <el-col :span="12">
              <el-form-item label="状态" prop="status">
                <el-select v-model="dialogForm.status" placeholder="请选择状态">
                  <el-option
                    v-for="item in enterpriseStatus"
                    :key="item.value"
                    :if="item.value!==2"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="商家位置">
                <el-button type="primary" @click="dialogMapVisible=true">
                  选择商家位置
                </el-button>
              </el-form-item>
              <el-dialog :visible.sync="dialogMapVisible" title="商家位置" width="62%">
                <!--                <BMapSelector :item-data="dialogForm" @location="changeLocation"></BMapSelector>-->
                <gaode-map v-if="this.dialogMapVisible" @change-location="changeLocation"/>

              </el-dialog>
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
      <!--      信息展示-->
      <el-table :data="totalData.records" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="编号" prop="id" width="80"></el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="名称" prop="name" width="150"></el-table-column>
        <el-table-column label="图片" prop="album" width="150">
          <template slot-scope="scope">
            <el-image
              :src="scope.row.album"
              style="width: 100px;"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="商家地址" prop="address" width="150"></el-table-column>
        <el-table-column label="状态" prop="status" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status===0" type="success">正常</el-tag>
            <el-tag v-if="scope.row.status===1" type="danger">停用</el-tag>
            <el-tag v-if="scope.row.status===2" type="warning">未审核</el-tag>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="创建日期" prop="create_time"></el-table-column>
        <el-table-column label="创建人" prop="create_by" width="80px"></el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="修改日期" prop="update_time"></el-table-column>
        <el-table-column label="修改人" prop="update_by" width="80px"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <span v-if="scope.row.status ===2">
              <el-popconfirm
                cancel-button-text="不了"
                confirm-button-text="是的"
                icon="el-icon-info"
                icon-color="red"
                title="是否通过该商家的审核？"
                @onConfirm="submitCheck(scope.row)"
              >
            <el-link slot="reference" icon="el-icon-check" type="success">审核通过</el-link>
              |</el-popconfirm>
            </span>
            <el-link icon="el-icon-edit" type="primary" @click="openDialog(2,scope.row)">编辑</el-link>
            |
            <el-link v-if="scope.row.status!==1" icon="el-icon-delete" type="danger" @click="submitDelete(scope.row)">
              删除
            </el-link>
          </template>
        </el-table-column>
      </el-table>
    </el-form>

    <el-pagination
      :current-page.sync="queryFrom.page"
      :page-size="queryFrom.size"
      :page-sizes="[10, 20, 30, 40,50]"
      :total="totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentPageChange"
    >
    </el-pagination>
  </div>
</template>
<script>
import {
  addEnterpriseData,
  changeEnterpriseState,
  checkPhoneIsExists,
  delEnterpriseData,
  editEnterpriseData,
  EnterpriseStatus,
  fetchEnterpriseData,
  findEnterpriseData
} from '@/api/enterprise'
import PageMixin from '@/mixin/PageMixin'
import GaodeMap from '@/components/Gaode'
import BMapSelector from '@/components/BaiduAddress'

export default {
  components: { BMapSelector, GaodeMap },
  mixins: [PageMixin],
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.dialogForm.login_pwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    let validateUsed = (rule, value, callback) => {
      const params = { id: this.dialogForm.id, phone: value }
      checkPhoneIsExists(params).then(resp => {
        if (resp.data) {
          callback(new Error(resp.message))
        } else {
          callback()
        }
      })
    }
    return {
      avatar: '',
      enterpriseStatus: EnterpriseStatus(),
      dialogMapVisible: false,
      rules: {
        phone: [
          { required: true, message: '不能为空', trigger: 'blur' },
          { pattern: '^0?(13|15|17|18|14)[0-9]{9}$', message: '电话号码格式不正确', trigger: 'blur' },
          { validator: validateUsed, trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入商家名称', trigger: 'blur' }
        ],
        login_pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 24, message: '长度在 6 到 24 个字符', trigger: 'blur' }
        ],
        album: [
          { required: true, message: '请上传商家图片', trigger: 'blur' }
        ],
        login_pwd_again: [{ validator: validatePass, trigger: 'blur' }],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        latitude: [
          { required: true, message: '请输入纬度', trigger: 'blur' }
        ],
        longitude: [
          { required: true, message: '请输入经度', trigger: 'blur' }
        ]
      }
    }
  }, methods: {
    // 坐标更改之后
    changeLocation(val) {
      console.log('接收到变更信息: ', val)
      this.dialogForm.latitude = val.latitude
      this.dialogForm.longitude = val.longitude
      this.dialogForm.address = val.address
      console.log(this.dialogForm)
      this.dialogMapVisible = false
    },
    fetchDataHook() {
      return fetchEnterpriseData
    },
    addDataHooK() {
      return addEnterpriseData
    },
    // 编辑框打开之前
    beforeEditDataHook(row) {
      const id = row ? row.id : this.ids[0]
      findEnterpriseData(id).then(resp => {
        this.dialogForm = resp.data
        this.avatar = this.dialogForm.album
      }).catch(err => {
          console.log(err)
          return 0
        }
      )
    },
    editDataHook() {
      return editEnterpriseData
    },
    submitCheck(row) {
      changeEnterpriseState({ id: row.id }).then(resp => {
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
    },
    delDataHook() {
      return delEnterpriseData
    },
    // 当手动键入封面图片时
    setAlbumOfAvatar() {
      this.avatar = this.dialogForm.album
    },
    // 上传头像成功后
    handleAvatarSuccess(res, file) {
      this.dialogForm.album = 'http://localhost:8080/' + res.data.fileUrl
      this.avatar = this.dialogForm.album
    }
  }
}
</script>
<style>
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

.amap-sug-result {
  top: 540px !important;
  z-index: 3000 !important;
  position: absolute;
}
</style>
