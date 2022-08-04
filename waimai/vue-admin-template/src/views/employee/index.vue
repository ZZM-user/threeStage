<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="账号">
        <el-input v-model="queryFrom.loginName" clearable placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item label="员工状态">
        <el-select v-model="queryFrom.status" clearable placeholder="员工状态">
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
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-col :span="4">
        <el-button icon="el-icon-plus" size="mini" style="background-color: #FFC200;color: black"
                   @click="openDialog(1)"
        >添加员工
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        align="center"
        label="编号"
        prop="id"
        width="50"
      />
      <el-table-column
        label="登录名"
        prop="login_name"
      />
      <el-table-column
        label="头像"
        prop="avatar"
      >
        <template slot-scope="scope">
          <el-image :src="scope.row.avatar" lazy></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="性别"
        prop="gender"
        width="80px"
      >
        <template slot-scope="scope">
          <el-tag :type="getGenderTagsColor(scope.row.gender)">{{
              scope.row.gender === 1 ? '男' : '女'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        prop="status"
        width="80px"
      >
        <template slot-scope="scope">
          <el-tag :type="getGenderTagsColor(scope.row.status)" effect="dark">{{
              scope.row.status === 1 ? '正常' : '停用'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建日期"
        prop="create_time"
        width="180px"
      />
      <el-table-column
        label="操作"
      >
        <template slot-scope="scope">
          <el-link :underline="false" type="primary" @click="openDialog(2,scope.row.login_name)">编辑</el-link>
          |
          <el-link :underline="false" type="danger" @click="changeAccountState(scope.row)">
            {{ scope.row.status === 1 ? '禁用' : '启用' }}
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleSizeChange"
    >
    </el-pagination>

    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="55%"
    >
      <el-form ref="formInline" :inline="true" :model="formInline" :rules="rules" class="demo-form-inline">
        <el-form-item label="登录名" prop="login_name">
          <el-input v-model="formInline.login_name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="formInline.gender" placeholder="请选择性别">
            <el-option :value="1" label="女"></el-option>
            <el-option :value="0" label="男"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号状态" prop="status">
          <template>
            <el-radio v-model="formInline.status" :label="0">禁用</el-radio>
            <el-radio v-model="formInline.status" :label="1">启用</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="头像">
          <el-input v-model="formInline.avatar"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="resetForm('formInline')">取 消</el-button>
    <el-button type="primary" @click="submitForm('formInline')">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>

import {
  addEmployeeInfo,
  changeEmployeeAccountStatus,
  EmployeeStatus,
  fetchEmployeeList,
  updateEmployeeInfo
} from '@/api/employee'

export default {
  data() {
    return {
      employeeStatus: EmployeeStatus(),
      tableData: [],
      total: 0,
      dialogType: 1,
      dialogTitle: '',
      dialogVisible: false,
      formInline: {
        login_name: '',
        avatar: '',
        gender: '',
        status: ''
      },
      rules: {
        login_name: [
          { required: true, message: '请输入登录名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择账号状态', trigger: 'blur' }
        ]
      },
      queryFrom: {
        page: 1,
        size: 10
      }
    }
  },
  computed: {},
  created() {
    this.fetchData()
  },
  methods: {
    // 获取表格等数据
    fetchData() {
      fetchEmployeeList(this.queryFrom).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.tableData = data.records
          this.total = data.totalRecord
        } else {
          this.$message.error(message)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    // 新增用户信息
    newEmployee(infoFrom) {
      addEmployeeInfo({ infoFrom }).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.tableData = data.records
          this.total = data.totalRecord
        } else {
          this.$message.error(message)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    // 修改用户信息
    updateEmployee(infoFrom) {
      updateEmployeeInfo({ infoFrom }).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.tableData = data.records
          this.total = data.totalRecord
        } else {
          this.$message.error(message)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    // 用于编辑前获取用户信息，绑定到dialog
    getEmployeeInfo(login_name) {
      const params = { page: this.currentPage, size: this.pageSize, loginName: login_name }
      fetchEmployeeList(params).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.formInline = data.records[0]
          this.total = data.totalRecord
        } else {
          this.$message.error(message)
        }
        console.log(this.formInline)
      }).catch(err => {
        console.log(err)
      })
    },
    // 用于搜索 用户信息
    searchEmployee() {
      const { loginName, status } = this.searchKeyWord
      const params = { page: this.currentPage, size: this.pageSize, loginName, status }
      fetchEmployeeList(params).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.tableData = data.records
          this.total = data.totalRecord
        } else {
          this.$message.error(message)
        }
        console.log(this.formInline)
      }).catch(err => {
        console.log(err)
      })
    },
    // 当总数发生变化
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchData()
    },
    // 获取用户性别所属tag颜色
    getGenderTagsColor(gender) {
      return gender === 0 ? 'danger' : 'success'
    },
    // 切换账户状态
    changeAccountState(emp) {
      const newState = emp.status === 1 ? 0 : 1
      const params = { eid: emp.id, status: newState }
      changeEmployeeAccountStatus(params).then(response => {
        const { code, message } = response
        const type = code === 0 ? 'success' : 'warning'
        this.$message({
          message: message,
          type: type
        })
      }).catch(err => {
        console.log(err)
      })
    },
    // 按照类型打开dialog(编辑/新增)
    openDialog(type, login_name) {
      this.resetForm('formInline')
      this.dialogType = type
      if (type === 1) {
        this.dialogTitle = '新增员工信息'
        this.dialogVisible = true
      } else if (type === 2) {
        this.getEmployeeInfo(login_name)
        this.dialogTitle = '编辑员工信息'
        this.dialogVisible = true
      }
    },
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogType === 1) {
            this.newEmployee(this.formInline)
          } else if (this.dialogType === 2) {
            this.updateEmployee(this.formInline)
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 重置表单
    resetForm(formName) {
      if (this.dialogVisible) {
        this.dialogVisible = false
      }
      if (this.$refs[formName] !== undefined) {
        this.$refs[formName].resetFields()
      }
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
  background-color: #ffffff;
}
</style>
