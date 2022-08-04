<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家id">
        <el-input v-model="queryFrom.eid" clearable placeholder="商家id"></el-input>
      </el-form-item>
      <el-form-item label="商品名称">
        <el-input v-model="queryFrom.name" clearable placeholder="商品名称"></el-input>
      </el-form-item>
      <el-form-item label="是否上架">
        <el-select v-model="queryFrom.isgrounding" clearable placeholder="上架状态">
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
        <el-button type="primary" @click="this.fetchData">查询</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col :span="3" style="line-height: 40px">
        <el-link :underline="false" type="primary">批量启售</el-link>
        |
        <el-link :underline="false" type="danger">
          批量停售
        </el-link>
      </el-col>
      <el-col :span="6">
        <el-button icon="el-icon-plus" style="background-color: #FFC200;color: black" @click="openDialog(1)">添加菜品
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="45px"
      >
      </el-table-column>
      <el-table-column
        label="菜品名称"
        prop="name"
        width="150"
      />
      <el-table-column
        label="图片"
        prop="picture"
      >
        <template slot-scope="scope">
          <el-image :src="scope.row.picture" lazy></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="菜品分类"
        prop="m_id"
      />
      <el-table-column
        label="售价"
        prop="price"
        width="80px"
      >
        <template slot-scope="scope">
          ￥{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column
        label="售卖状态"
        prop="isgrounding"
        width="80px"
      >
        <template slot-scope="scope">
          <el-tag :type="getGenderTagsColor(scope.row.isgrounding)" effect="dark">{{
              scope.row.isgrounding === 1 ? '启售' : '停售'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="最后操作日期"
        prop="update_time"
        width="180px"
      />
      <el-table-column
        label="操作"
      >
        <template slot-scope="scope">
          <el-link :underline="false" type="primary" @click="openDialog(2,scope.row.name)">编辑</el-link>
          |
          <el-link :underline="false" type="primary">
            {{ scope.row.isgrounding === 1 ? '停售' : '启售' }}
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
        <el-form-item label="菜品名" prop="name">
          <el-input v-model="formInline.name"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="m_id">
          <el-select v-model="formInline.m_id" placeholder="请选择">
            <el-option :value="1" label="xx"></el-option>
            <el-option :value="0" label="yy"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜品状态" prop="isgrounding">
          <template>
            <el-radio v-model="formInline.isgrounding" :label="0">停售</el-radio>
            <el-radio v-model="formInline.isgrounding" :label="1">启售</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-input v-model.number="formInline.price"></el-input>
        </el-form-item>
        <el-form-item label="商品图片">
          <el-input v-model="formInline.picture"></el-input>
        </el-form-item>
        <el-form-item label="商家" prop="b_id">
          <el-select v-model="formInline.b_id" placeholder="请选择">
            <el-option :value="1" label="xx"></el-option>
            <el-option :value="0" label="yy"></el-option>
          </el-select>
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
  addMerchandiseInfo,
  changeMerchandiseAccountStatus,
  fetchMerchandiseList,
  MerchandiseStatus,
  updateMerchandiseInfo
} from '@/api/merchandise'

export default {
  data() {
    return {
      tableData: [],
      total: 0,
      dialogType: 1,
      dialogTitle: '',
      dialogVisible: false,
      formInline: {
        name: '',
        m_id: '',
        b_id: '',
        picture: '',
        price: '',
        isgrounding: ''
      },
      multipleSelection: [],
      rules: {
        name: [
          { required: true, message: '请输入登录名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        m_id: [{
          required: true, message: '请选择分类', trigger: 'blur'
        }],
        b_id: [{
          required: true, message: '请选择分类', trigger: 'blur'
        }],
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' }
        ],
        isgrounding: [
          { required: true, message: '请选择商品状态', trigger: 'blur' }
        ]
      },
      queryFrom: {
        page: 1,
        size: 10
      },
      merchandiseStatus: MerchandiseStatus()
    }
  },
  computed: {},
  created() {
    this.fetchData()
  },
  methods: {
    // 获取表格等数据
    fetchData() {
      fetchMerchandiseList(this.queryFrom).then(response => {
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
    newMerchandise(infoFrom) {
      addMerchandiseInfo({ infoFrom }).then(response => {
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
    updateMerchandise(infoFrom) {
      updateMerchandiseInfo({ infoFrom }).then(response => {
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
    getMerchandiseInfo(name) {
      const params = { page: this.currentPage, size: this.pageSize, name }
      fetchMerchandiseList(params).then(response => {
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
    // 获取用户性别所属tag颜色
    getGenderTagsColor(gender) {
      return gender === 0 ? 'danger' : 'success'
    },
    // 切换账户状态
    changeAccountState(emp) {
      const newState = emp.status === 1 ? 0 : 1
      const params = { eid: emp.id, status: newState }
      changeMerchandiseAccountStatus(params).then(response => {
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
        this.getMerchandiseInfo(login_name)
        this.dialogTitle = '编辑员工信息'
        this.dialogVisible = true
      }
    },
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogType === 1) {
            this.newMerchandise(this.formInline)
          } else if (this.dialogType === 2) {
            this.updateMerchandise(this.formInline)
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
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
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
