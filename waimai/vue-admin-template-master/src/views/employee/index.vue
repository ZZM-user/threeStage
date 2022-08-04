<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="登录名">
        <el-input v-model="queryFrom.loginName" placeholder="登录名"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryFrom.status" placeholder="状态">
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

    <el-table :data="totalData.records" style="width: 100%">
      <el-table-column label="编号" prop="id" width="60"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="登录名" prop="login_name" width="150"></el-table-column>
      <el-table-column label="性别" prop="gender" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.gender===1" type="success">男</el-tag>
          <el-tag v-if="scope.row.gender===0" type="danger">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="头像" prop="avatar" width="150">
        <template slot-scope="scope">
          <el-avatar :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" effect="dark" type="success">正常</el-tag>
          <el-tag v-if="scope.row.status==1" effect="dark" type="danger">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="create_time"></el-table-column>
      <el-table-column label="创建人" prop="create_by"></el-table-column>
      <el-table-column label="修改时间" prop="update_time"></el-table-column>
      <el-table-column label="修改人" prop="update_by"></el-table-column>
    </el-table>
    <el-pagination
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :page-sizes="[10, 20, 30, 40,50]"
      :total="totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="this.fetchData"
    >
    </el-pagination>
  </div>
</template>

<script>
import { EmployeeStatus, fetchEmployeeData } from '@/api/employee'

export default {
  data() {
    return {
      totalData: {
        totalRecord: 1,
        records: []
      },
      employeeStatus: EmployeeStatus(),
      queryFrom: {
        page: 1,
        size: 10
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      fetchEmployeeData(this.queryFrom).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.totalData = data
        }
      }).catch(error => {
        console.log(error)
      })
    },
    handleSizeChange(val) {
      this.queryFrom.size = val
    }

  }
}
</script>

<style scoped>
div {
  padding: 1%;
}
</style>
