<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="经销商">
        <el-input v-model="queryFrom.dealerName" clearable placeholder="经销商名称"></el-input>
      </el-form-item>
      <el-form-item label="大区">
        <el-input v-model="queryFrom.orgName" clearable placeholder="大区名称"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="queryFrom.phone" clearable placeholder="电话"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="totalData.records" stripe style="width: 100%">
      <el-table-column :show-overflow-tooltip="true" label="经销商ID" prop="dealer_id" width="160"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="经销商编码" prop="dealer_code" width="100"
      ></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="公司名称" prop="company_name" width="240"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="大区名称" prop="org_name"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="地址" prop="address" width="300"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="电话" prop="phone"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="类型" prop="data_type"></el-table-column>
    </el-table>

    <el-pagination
      :current-page="this.queryFrom.page"
      :hide-on-single-page="true"
      :page-size="this.queryFrom.size"
      :page-sizes="[10, 20, 30, 40,50]"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import { fetchCarData } from '@/api/car'

export default {
  name: 'index',
  data() {
    return {
      queryFrom: {
        page: 1,
        size: 10
      },
      totalData: {
        totalRecord: 0,
        records: []
      }
    }
  }, created() {
    this.fetchData()
  }, methods: {
    fetchData() {
      fetchCarData(this.queryFrom).then(response => {
        this.totalData = response.data
      })
    },
    onSubmit() {
      this.queryFrom.page = 1
      this.fetchData()
    },
    handleSizeChange(val) {
      this.queryFrom.size = val
      this.fetchData()
    },
    handleCurrentChange(val) {
      this.queryFrom.page = val
      this.fetchData()
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
}
</style>
