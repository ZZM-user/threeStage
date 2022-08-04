<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家ID">
        <el-input v-model="queryFrom.eid" clearable placeholder="商家id"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-input v-model="queryFrom.name" clearable placeholder="分类名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="totalData.records" style="width: 100%">
      <el-table-column label="编号" prop="id" width="80"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="所属商户" prop="enterprise_id" width="80"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="名称" prop="name" width="180"></el-table-column>
      <el-table-column label="图片" prop="picture" width="150">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.picture"
            style="width: 100px;"
          ></el-image>
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
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="fetchData"
    >
    </el-pagination>
  </div>
</template>

<script>
import { fetchCategoryData } from '@/api/category'

export default {
  data() {
    return {
      totalData: {
        totalRecord: 0,
        records: []
      },
      queryFrom: {
        page: 1,
        size: 10
      }
    }
  }, created() {
    this.fetchData()
  }, methods: {
    fetchData() {
      fetchCategoryData(this.queryFrom).then(response => {
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
