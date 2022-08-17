<template>
  <div id="main">
    <el-row :gutter="20" justify="space-between">
      <el-col :span="6">
        <el-card shadow="always">
          总是显示
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always">
          总是显示
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always">
          总是显示
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always">
          总是显示
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" justify="space-between" style="margin-top: 1%">
      <el-col :span="12">
        <el-card shadow="always" style="height: 500px">
          总是显示
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="always" style="height: 500px">
          总是显示
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>

export default {
  name: 'Dashboard'
  // computed: {
  //   ...mapGetters([
  //     'name'
  //   ])
  // },
  // methods: {}
}
</script>

<style lang="scss" scoped>
#main {
  padding: 1%;
}
</style>
