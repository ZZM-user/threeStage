<template>
  <div id="main">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="按年月结单">
        <el-date-picker
          v-model="value2"
          placeholder="选择年月"
          type="month">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>

      <!--      信息展示-->
      <el-table-column label="日期" width="120">
        <el-table
          ref="multipleTable"
          :data="totalData.records"
          style="width: 100%"
          tooltip-effect="dark"
          @selection-change="han">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column label="编号" prop="id" width="80"></el-table-column>
          <el-table-column label="姓名" prop="name" width="120"></el-table-column>
          <el-table-column label="房间号" prop="room" show-overflow-tooltip></el-table-column>
          <template slot-scope="scope">{{ scope.row.date }}</template>
        </el-table>
      </el-table-column>
    </el-form>
  </div>
</template>

<script>
import PageMixin from "@/mixin/PageMixin";

export default {
  name: "index",
  mixins: [PageMixin],
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>
