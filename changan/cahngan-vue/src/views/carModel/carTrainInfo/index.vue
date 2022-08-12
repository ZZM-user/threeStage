<template>
  <div id="main">
    <!--    工具栏-->
    <el-col :span="6">
      <el-button size="small" type="primary">添加车型</el-button>
    </el-col>
    <el-col :offset="12" :span="6">
      <el-input v-model="queryFrom.keyword" clearable placeholder="请输入品牌或车系" size="small">
        <el-button slot="append" icon="el-icon-search" @click="this.fetchData"/>
      </el-input>
    </el-col>
    <!--    展示信息-->
    <el-table :data="totalData.records" style="width: 100%">
      <el-table-column label="品牌" prop="brandCode"></el-table-column>
      <el-table-column label="车系" prop="trainName"></el-table-column>
      <el-table-column label="操作">
        <el-link type="primary">查看</el-link>
        |
        <el-link type="primary">修改</el-link>
      </el-table-column>
    </el-table>
    <el-pagination
      :background="true"
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :page-sizes="[10, 20, 30, 40, 50]"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import PageMixin from '@/mixin/PageMixin'
import { fetchCarTrainInfo } from '@/api/CarTrainInfo'

export default {
  mixins: [PageMixin],
  data() {
    return {}
  }, created() {
    this.fetchData()
  }, methods: {
    fetchDataHook() {
      return fetchCarTrainInfo
    }
  }
}
</script>

<style scoped>
#main {
  padding: 1%;
}

</style>
