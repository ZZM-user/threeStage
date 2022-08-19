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
        width="55"/>
      <el-table-column label="姓名" prop="employee"></el-table-column>
      <el-table-column label="房间号" prop="room" show-overflow-tooltip></el-table-column>
      <el-table-column label="入住时间">
        <template slot-scope="scope">
          {{ scope.row.enterDate }}
        </template>
      </el-table-column>
      <el-table-column label="预计退房">
        <template slot-scope="scope">
          <span v-if="scope.row.leaveDate">
            {{ scope.row.leaveDate }}
          </span>
          <span v-else>无</span>
        </template>
      </el-table-column>
      <el-table-column label="实际退房">
        <template slot-scope="scope">
          <span v-if="scope.row.realityDate">
          {{ scope.row.realityDate }}
          </span>
          <span v-else>无</span>
        </template>
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
import {fetchRecordData} from "@/api/record";

export default {
  name: "index",
  mixins: [PageMixin],
  data() {
    return {}
  }, methods: {
    fetchDataHook() {
      return fetchRecordData
    }
  }
}
</script>

<style scoped>

</style>
