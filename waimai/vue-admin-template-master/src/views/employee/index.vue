<template xmlns:el-col="http://www.w3.org/1999/html">
  <div id="main">
    <!--    搜索栏-->
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline" size="small" style="height: 60px">
      <el-form-item label="登录名">
        <el-input v-model="queryFrom.loginName" placeholder="登录名" size="small"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryFrom.status" placeholder="状态" size="small">
          <el-option
            v-for="item in employeeStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" @click="submitSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <!--    工具栏-->
    <el-row>
      <el-col>
        <el-button icon="el-icon-plus" size="small" type="success" @click="openDialog(1)">新增</el-button>
        <el-button :disabled="ableEdit" icon="el-icon-edit" size="small" type="warning" @click="openDialog(2)">修改
        </el-button>
        <el-button :disabled="ableDelete" icon="el-icon-delete" size="small" type="danger" @click="submitDelete">删除
        </el-button>
      </el-col>
    </el-row>
    <!--    dialog弹框-->
    <el-dialog :closed="closeDialog" :title="dialogTitle" :visible.sync="dialogVisible" width="60%">
      <el-form ref="dialogForm" :model="dialogForm" :rules="rules" class="demo-ruleForm" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="登录名" prop="login_name">
              <el-input v-model="dialogForm.login_name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="dialogForm.gender" placeholder="请选择性别">
                <el-option :value="1" label="男"></el-option>
                <el-option :value="0" label="女"></el-option>
              </el-select>
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
            <el-form-item label="头像" prop="avatar">
              <el-upload
                :before-upload="beforeAvatarUpload"
                :drag="true"
                :on-success="handleAvatarSuccess"
                :show-file-list="false"
                action="http://localhost:8080/img/upload"
                class="avatar-uploader"
              >
                <img v-if="avatar" :src="avatar" alt="头像" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="dialogForm.status" default-first-option placeholder="请选择状态">
                <el-option
                  v-for="item in employeeStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
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
    <!--    信息展示栏-->
    <el-table ref="multipleSelection" :data="totalData.records"
              style="width: 100%" @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="编号" prop="id" width="60"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="登录名" prop="login_name" width="150"></el-table-column>
      <el-table-column label="性别" prop="gender" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.gender===1" type="success">男</el-tag>
          <el-tag v-if="scope.row.gender===0" type="danger">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="头像" prop="avatar" width="120">
        <template slot-scope="scope">
          <el-avatar :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" width="80">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#b2bec3"
            @change="submitChangeStatus(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="create_time"></el-table-column>
      <el-table-column label="创建人" prop="create_by"></el-table-column>
      <el-table-column label="修改时间" prop="update_time"></el-table-column>
      <el-table-column label="修改人" prop="update_by"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <span v-if="scope.row.status===2">
            <el-link icon="el-icon-edit" type="primary" @click="approved(scope.row)">通过审核</el-link>
              |
          </span>
          <el-link icon="el-icon-edit" type="primary" @click="openDialog(2,scope.row)">编辑</el-link>
          |
          <el-link icon="el-icon-delete" type="danger" @click="submitDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
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
  addEmployeeData,
  changeEmployeeState,
  checkLoginNameIsExists,
  delEmployeeData,
  editEmployeeData,
  EmployeeStatus,
  fetchEmployeeData,
  findEmployeeData
} from '@/api/employee'
import PageMixin from '@/mixin/PageMixin'

export default {
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
    let validateLoginName = (rule, value, callback) => {
      checkLoginNameIsExists({ id: this.dialogForm.id, loginName: value }).then(response => {
        if (response.code !== 0) {
          callback(new Error(response.message))
        } else {
          callback()
        }
      })
    }
    return {
      employeeStatus: EmployeeStatus(),
      // 上传头像的中介载体
      avatar: '',
      rules: {
        login_name: [
          { required: true, message: '请输入登录名', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' },
          { validator: validateLoginName, trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        login_pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 24, message: '长度在 6 到 24 个字符', trigger: 'blur' }
        ],
        login_pwd_again: [{ validator: validatePass, trigger: 'blur' }],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    fetchDataHook() {
      return fetchEmployeeData
    },
    addDataHooK() {
      return addEmployeeData
    },
    // 编辑前获取用户数据
    beforeEditDataHook(row) {
      const id = row ? row.id : this.ids[0]
      findEmployeeData(id).then(response => {
        this.dialogForm = response.data
        this.avatar = this.dialogForm.avatar
      }).catch(error => {
        console.log(error)
        return 0
      })
    },
    editDataHook() {
      return editEmployeeData
    },
    delDataHook() {
      return delEmployeeData
    },
    // 改变状态
    changeStatusHook() {
      return changeEmployeeState
    },
    // 上传头像成功后
    handleAvatarSuccess(res, file) {
      this.dialogForm.avatar = 'http://localhost:8080/' + res.data.fileUrl
      this.avatar = this.dialogForm.avatar
    },
  }
}
</script>

<style scoped>
#main {
  padding: 1%;
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
  border-radius: 50%;
}

.avatar {
  width: 178px;
  height: 178px;
  border-radius: 50%;
  display: block;
}
</style>
