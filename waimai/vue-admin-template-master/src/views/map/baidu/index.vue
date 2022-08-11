<template>
  <BMapSelector @location="changeLocation"></BMapSelector>
</template>

<script>
import BMapSelector from '@/components/BaiduAddress'

export default {
  name: 'index',
  components: { BMapSelector },
  methods: {
    changeLocation(val) {
      console.log(val)
    }
  }
}
</script>

<style scoped>

</style>
