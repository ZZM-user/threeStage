<template>
  <div>
    <el-form :inline="true" :model="queryForm" class="demo-form-inline">
      <el-form-item label="商家名称">
        <el-input v-model="queryForm.name" placeholder="商家名称" size="small"></el-input>
      </el-form-item>
      <el-form-item label="商家电话">
        <el-input v-model="queryForm.phone" placeholder="商家电话" size="small"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model.number="queryForm.status" placeholder="状态" size="small">
          <el-option
            v-for="item in stateList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建日期">
        <el-date-picker v-model="createTimeValue"
                        end-placeholder="结束日期" range-separator="至" size="small"
                        start-placeholder="开始日期" type="daterange" value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="this.tableResult.records" style="width: 100%">
      <el-table-column label="编号" prop="id" width="80"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="商家名称" prop="name" width="200"></el-table-column>
      <el-table-column label="商家图片" prop="album" width="200">
        <template slot-scope="scope">
          <el-image :src="scope.row.album" lazy></el-image>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" prop="phone" width="180"></el-table-column>
      <el-table-column label="状态" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status===0" type="primary">正常</el-tag>
          <el-tag v-if="scope.row.status===1" type="warning">停用</el-tag>
          <el-tag v-if="scope.row.status===2" type="danger">未审核</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" prop="audit_time" width="200"></el-table-column>
      <el-table-column label="创建时间" prop="create_time" width="200"></el-table-column>
    </el-table>
    <el-pagination
      :current-page="this.queryForm.page"
      :page-size="this.queryForm.size"
      :page-sizes="[10, 20, 30, 40, 50]"
      :total="this.tableResult.totalRecords"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="this.fetchData"
    >
    </el-pagination>
  </div>
</template>

<script>
import { fetchEnterpriseList, stateListApi } from '@/api/enterprise'

export default {
  data() {
    return {
      createTimeValue: [],
      queryForm: {
        page: 1,
        size: 10
      },
      stateList: stateListApi(),
      tableResult: {
        totalRecords: 0,
        records: []
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      delete this.queryForm.startDate
      delete this.queryForm.endDate
      if (this.createTimeValue && this.createTimeValue.length === 2) {
        this.queryForm.startDate = this.createTimeValue[0]
        this.queryForm.endDate = this.createTimeValue[1]
      }

      fetchEnterpriseList(this.queryForm).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.tableResult.totalRecords = data.totalRecord
          this.tableResult.records = data.records
        }
      })
    },
    handleSizeChange(val) {
      this.queryForm.size = val
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
}
</style>
