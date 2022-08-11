<template>
  <div class="show-map">
    <div class="map" ref="map">
    	    <!-- 地图实例 -->
    	    <baidu-map class="bmView"  :center="location" :zoom="zoom" ak="3LgMlTyAIzk2ok2Sn5ZY6NPPmVQjMFch">
              <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
               <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
    	        <!-- 地图显示 -->
    	       <bm-view style="width: 100%; height:400px; flex: 1"></bm-view>
    	        <!-- 标记点组件 -->
               <bm-marker v-if="location.lng && location.lat" :position="{ lng: location.lng, lat: location.lat }"></bm-marker>
    	    </baidu-map>
    </div>
  </div>
</template>

<script>
  // 导入地图组件对象
  import {BaiduMap,BmView,BmMarker,BmControl,BmMapType,BmNavigation} from 'vue-baidu-map'

  export default {
    props: {
      itemData: {
        type: Object,
        required:true
      }
    },
    // 挂载地图级组件
    components:{
      BaiduMap,
      BmView,
      BmMarker,
      BmControl,
      BmMapType,
      BmNavigation
    },
    data(){
      return {
        // 地图显示的中心坐标   lng经度106.4729391，lat纬度29.6315839
        location: {
          lng: undefined,
          lat: undefined,
        },
        // 缩放，15基本上就可以看附近的一些街道了 （1-21）
        zoom: 15,
      }
    },
    created() {
      // 初始化商家信息
      if(this.itemData.id){
        this.location.lng=this.itemData.longitude
        this.location.lat=this.itemData.latitude
      }
    }
  }
</script>

<style>
  .BMap_cpyCtrl {
    display: none;
  }

  .anchorBL {
    display: none;
  }
</style>
