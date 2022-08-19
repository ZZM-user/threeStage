<template>
  <div id="main">
    <el-table
      ref="multipleTable"
      :data="totalData.records"
      style="width: 100%"
      tooltip-effect="dark"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="编号"
        prop="id">
      </el-table-column>
      <el-table-column
        label="姓名"
        prop="name">
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :page-sizes="[10, 20, 30, 40, 50]"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentPageChange">
    </el-pagination>
  </div>
</template>

<script>
import PageMixin from "@/mixin/PageMixin";
import {fetchEmployeeData} from "@/api/employee";

export default {
  name: "index",
  mixins: [PageMixin],
  data() {
    return {}
  }, methods: {
    fetchDataHook() {
      return fetchEmployeeData
    }
  }
}
</script>

<style scoped>

</style>
