<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家名称">
        <el-input v-model="queryFrom.name" clearable placeholder="商家名称"></el-input>
      </el-form-item>
      <el-form-item label="商家电话">
        <el-input v-model="queryFrom.phone" clearable placeholder="商家电话"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryFrom.status" clearable placeholder="商家状态">
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
          start-placeholder="开始日期"
          type="daterange"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>

      <el-table :data="totalData.records" style="width: 100%">
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
        <el-table-column :show-overflow-tooltip="true" label="地址" prop="address" width="150"></el-table-column>
        <el-table-column label="电话" prop="phone" width="110"></el-table-column>
        <el-table-column label="状态" prop="status" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status===0" type="success">正常</el-tag>
            <el-tag v-if="scope.row.status===1" type="danger">停用</el-tag>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="创建日期" prop="create_time"></el-table-column>
        <el-table-column label="创建人" prop="create_by"></el-table-column>
        <el-table-column :show-overflow-tooltip="true" label="修改日期" prop="update_time"></el-table-column>
        <el-table-column label="修改人" prop="update_by"></el-table-column>
      </el-table>
    </el-form>

    <el-pagination
      :current-page.sync="queryFrom.page"
      :page-size="queryFrom.size"
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
import { EnterpriseStatus, fetchEnterpriseData } from '@/api/enterprise'
import PageMixin from '@/mixin/PageMixin'

export default {
  mixins: [PageMixin],
  data() {
    return {
      enterpriseStatus: EnterpriseStatus()
    }
  }, methods: {
    fetchDataHook() {
      return fetchEnterpriseData
    }
  }
}
</script>
<style scoped>
div {
  padding: 1%;
}
</style>
