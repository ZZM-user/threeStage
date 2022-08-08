<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="品牌" prop="date">
      </el-table-column>
      <el-table-column label="车系" prop="name">
      </el-table-column>
      <el-table-column label="操作" prop="address">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'index'
}
</script>

<style scoped>

</style>
