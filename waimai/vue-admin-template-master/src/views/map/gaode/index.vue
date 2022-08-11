<template>
  <gaode-map @change-location="changeLocation"></gaode-map>
</template>

<script>
import GaodeMap from '@/components/Gaode'

export default {
  name: 'index',
  components: { GaodeMap },
  methods: {
    changeLocation(val) {
      console.log(val)
    }
  }
}
</script>

<style scoped>

</style>
