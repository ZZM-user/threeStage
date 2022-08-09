<template>
  <div id="main">
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="登录名">
        <el-input v-model="queryFrom.loginid" clearable placeholder="登录名" size="small"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryFrom.status" clearable placeholder="状态" size="small">
          <el-option v-for="item in contamerStatus"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建日期">
        <el-date-picker
          v-model="createDate"
          clearable
          end-placeholder="结束日期"
          range-separator="至"
          size="small"
          start-placeholder="开始日期"
          type="daterange"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" @click="fetchData()">查询</el-button>
      </el-form-item>
    </el-form>
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
            <el-form-item label="账号" prop="loginId">
              <el-input v-model="dialogForm.loginId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称" prop="user_name">
              <el-input v-model="dialogForm.user_name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <!--  不允许通过编辑来修改密码  -->
        <el-row v-if="dialogType===1">
          <el-col :span="12">
            <el-form-item label="登录密码" prop="passwold">
              <el-input v-model="dialogForm.passwold" type="password"></el-input>
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
            <el-form-item label="性别" prop="gender" width="80">
              <el-radio v-model="dialogForm.gender" :label="1">男</el-radio>
              <el-radio v-model="dialogForm.gender" :label="0">女</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status" width="80">
              <el-radio v-model="dialogForm.status" :label="0">正常</el-radio>
              <el-radio v-model="dialogForm.status" :label="1">停用</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="头像" prop="portrait">
              <el-input v-model="dialogForm.portrait" placeholder="请输入头像图片地址" @change="setAlbumOfAvatar"/>
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
    <el-table :data="this.totalData.records" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column label="编号" prop="id" width="50"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="名称" prop="user_name" width="150"></el-table-column>
      <el-table-column label="头像" prop="portrait" width="150">
        <template slot-scope="scope">
          <el-avatar :src="scope.row.portrait"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="性别" prop="gender" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.gender===1" type="success">男</el-tag>
          <el-tag v-if="scope.row.gender===0" type="danger">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status===0" effect="dark" type="success">正常</el-tag>
          <el-tag v-if="scope.row.status===1" effect="dark" type="danger">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" prop="create_time"></el-table-column>
      <el-table-column label="创建人" prop="create_by"></el-table-column>
      <el-table-column label="修改日期" prop="update_time"></el-table-column>
      <el-table-column label="修改人" prop="update_by"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link icon="el-icon-edit" type="primary" @click="openDialog(2,scope.row)">编辑</el-link>
          |
          <el-link icon="el-icon-delete" type="danger" @click="submitDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page="queryFrom.page"
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
  addContamerData,
  changeContamerState,
  checkLoginIdIsExists,
  ContamerStatus,
  delContamerData,
  editContamerData,
  fetchContamerData,
  findContamerData
} from '@/api/contamer'
import PageMixin from '@/mixin/PageMixin'

export default {
  mixins: [PageMixin],
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.dialogForm.passwold) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    let validateUsed = (rule, value, callback) => {
      const params = { id: this.dialogForm.id, loginId: value }
      checkLoginIdIsExists(params).then(resp => {
        if (resp.data) {
          callback(new Error(resp.message))
        } else {
          callback()
        }
      })
    }
    return {
      avatar: '',
      contamerStatus: ContamerStatus(),
      rules: {
        loginId: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { validator: validateUsed, trigger: 'blur' }
        ],
        user_name: [
          { required: true, message: '请输入昵称', trigger: 'blur' }
        ],
        passwold: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 24, message: '长度在 6 到 24 个字符', trigger: 'blur' }
        ],
        portrait: [
          { required: true, message: '请上传头像', trigger: 'blur' }
        ],
        login_pwd_again: [{ validator: validatePass, trigger: 'blur' }],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    fetchDataHook() {
      return fetchContamerData
    },
    addDataHooK() {
      return addContamerData
    },
    // 编辑框打开之前
    beforeEditDataHook(row) {
      const id = row.id ? row.id : this.ids[0]
      findContamerData(id).then(resp => {
        this.dialogForm = resp.data
        this.avatar = this.dialogForm.portrait
      }).catch(err => {
          console.log(err)
          return 0
        }
      )
    },
    editDataHook() {
      return editContamerData
    },
    submitCheck(row) {
      changeContamerState({ id: row.id }).then(resp => {
        const { code, message, data } = resp
        if (code === 0) {
          this.$message({
            message: message,
            type: 'success'
          })
        } else {
          this.$message({
            message: message || '操作失败！',
            type: 'danger'
          })
        }
      })
    },
    delDataHook() {
      return delContamerData
    },
    // 当手动键入封面图片时
    setAlbumOfAvatar() {
      this.avatar = this.dialogForm.portrait
    },
    // 上传头像成功后
    handleAvatarSuccess(res, file) {
      this.dialogForm.portrait = 'http://localhost:8080/' + res.data.fileUrl
      this.avatar = this.dialogForm.portrait
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
  border-radius: 50%;
  border: 1px solid #f1f2f6;
}
</style>
