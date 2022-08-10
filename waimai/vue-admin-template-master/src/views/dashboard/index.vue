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
    <gaode-map @change-location="changeLocation"></gaode-map>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import GaodeMap from '@/components/Gaode'

export default {
  name: 'Dashboard',
  components: { GaodeMap },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    changeLocation(val) {
      console.log(val)
    }
  }
}
</script>

<style lang="scss" scoped>
#main {
  padding: 1%;
}
</style>
