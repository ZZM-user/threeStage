<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家id">
        <el-input v-model="queryFrom.eid" placeholder="商家id"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-input v-model="queryFrom.name" placeholder="菜品名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="20">
      <el-col :span="6">
        <el-button icon="el-icon-plus" style="background-color: #FFC200;color: black"
                   @click="openDialog(1)"
        >新增商品分类
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        align="center"
        label="分类名称"
        prop="name"
        width="100"
      />
      <el-table-column
        label="口味图片"
        prop="picture"
      >
        <template slot-scope="scope">
          <el-image :src="scope.row.picture" lazy></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="创建日期"
        prop="create_time"
        width="180px"
      />
      <el-table-column
        label="更新时间"
        prop="update_time"
        width="150px"
      >
      </el-table-column>
      <el-table-column
        label="更新人"
        prop="update_by"
        width="180px"
      >
      </el-table-column>
      <el-table-column
        label="操作"
      >
        <template slot-scope="scope">
          <el-link :underline="false" type="primary" @click="openDialog(2,scope.row.name)">修改</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="currentPage"
      :page-size="pageSize"
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
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="formInline.name"></el-input>
        </el-form-item>
        <el-form-item label="商家" prop="enterprise_id">
          <el-select v-model="formInline.enterprise_id" placeholder="请选择性别">
            <el-option :value="1" label="兰州拉面"></el-option>
            <el-option :value="0" label="大排档"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="口味图片">
          <el-input v-model="formInline.picture"></el-input>
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

import { addCategoryInfo, fetchCategoryList, updateCategoryInfo } from '@/api/category'

export default {
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      dialogType: 1,
      dialogTitle: '',
      dialogVisible: false,
      formInline: {
        name: '',
        enterprise_id: '',
        avatar: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入分类名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        enterprise_id: [
          { required: true, message: '请选择商家', trigger: 'blur' }
        ]
      },
      queryFrom: {
        eid: '',
        name: ''
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
      const params = { page: this.currentPage, size: this.pageSize }
      fetchCategoryList(params).then(response => {
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
    newCategory(infoFrom) {
      addCategoryInfo({ infoFrom }).then(response => {
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
    updateCategory(infoFrom) {
      updateCategoryInfo({ infoFrom }).then(response => {
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
    getCategoryInfo(name) {
      const params = { page: this.currentPage, size: this.pageSize, name: name, accountType: 1 }
      fetchCategoryList(params).then(response => {
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
    // 当总数发生变化
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchData()
    },
    // 按照类型打开dialog(编辑/新增)
    openDialog(type, login_name) {
      this.resetForm('formInline')
      this.dialogType = type
      if (type === 1) {
        this.dialogTitle = '新增分类信息'
        this.dialogVisible = true
      } else if (type === 2) {
        this.getCategoryInfo(login_name)
        this.dialogTitle = '编辑分类信息'
        this.dialogVisible = true
      }
    },
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogType === 1) {
            this.newCategory(this.formInline)
          } else if (this.dialogType === 2) {
            this.updateCategory(this.formInline)
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
