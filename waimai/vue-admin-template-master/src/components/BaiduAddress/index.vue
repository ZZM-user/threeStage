<template>
  <div>

    <el-form ref="baiForm" :model="businessDetail" label-width="110px">
      <el-form-item label="搜索商家地址：" prop="address">
        <el-input v-model="keyword" placeholder="请输入地址" style="width: 500px;"/>
      </el-form-item>
      <el-form-item label="选择中的值：">
        {{ businessDetail.address }}
      </el-form-item>
    </el-form>

    <baidu-map :center="mapData.center" :zoom="mapData.zoom" ak="3LgMlTyAIzk2ok2Sn5ZY6NPPmVQjMFch" class="bm-view"
               @ready="mapHandler"
    >
      <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
      <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
      <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>
      <bm-local-search v-show="false" :auto-viewport="true" :keyword="keyword" @searchcomplete="searchcomplete"
      ></bm-local-search>
      <bm-geolocation
        :autoLocation="true"
        :showAddressBar="true"
        anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
      ></bm-geolocation>

    </baidu-map>

    <el-row>
      <el-col :span="24" class="rt">
        <el-button type="primary" @click="chooseLocation">确定位置</el-button>
      </el-col>
    </el-row>


  </div>
</template>

<script>
// 导入地图组件对象

import {
  BaiduMap,
  BmAutoComplete,
  BmCityList,
  BmControl,
  BmGeolocation,
  BmLocalSearch,
  BmMapType,
  BmMarker,
  BmNavigation
} from 'vue-baidu-map'

export default {
  name: 'BMapSelector',
  components: {
    BaiduMap,
    BmNavigation,
    BmGeolocation,
    BmCityList,
    BmMarker,
    BmControl,
    BmAutoComplete,
    BmMapType,
    BmLocalSearch
  },
  // 公共暴露属性(不要改)
  props: {
    itemData: {
      type: Object,
      default: () => {
      }
    }
  },
  data() {
    return {
      //搜索的值
      keyword: undefined,
      //商家信息(包含坐标信息)
      businessDetail: {
        address: undefined,
        latitude: undefined,
        longitude: undefined
      },
      //搜索出来的地理位置
      mapMarkers: [],
      //地图数据
      mapData: {
        //中心坐标
        center: {
          lng: 106.556901,
          lat: 29.570045
        },
        //缩放级别
        zoom: 13
      },
      //BMap类
      BMap: null,
      //地图对象
      map: null
    }
  },
  created() {
    console.log(this.itemData)
    if (this.itemData.address) {
      this.keyword = this.businessDetail.address
      this.businessDetail.address = this.itemData.address
      this.businessDetail.latitude = this.itemData.latitude
      this.businessDetail.longitude = this.itemData.longitude
    } else {
      this.keyword = undefined
    }
  },
  methods: {
    //地图预处理
    async mapHandler({
      BMap,
      map
    }) {
      //保存百度地图类
      this.BMap = BMap
      //保存地图对象
      this.map = map
      //如果一开始坐标存在(编辑的时候)
      if (this.itemData && this.itemData.address) {
        this.businessDetail.address = this.itemData.address
      }

      if (this.itemData && this.itemData.longitude && this.itemData.latitude) {
        //设置坐标
        this.mapData.center.lng = this.businessDetail.longitude = this.itemData.longitude
        this.mapData.center.lat = this.businessDetail.latitude = this.itemData.latitude
        //创建定位标记
        let marker = new BMap.Marker(
          new BMap.Point(
            this.businessDetail.longitude,
            this.businessDetail.latitude
          )
        )
        //将标记添加到地图上
        map.addOverlay(marker)
        this.zoom = 13
      }

    },
    //确定选择坐标
    chooseLocation() {
      if (this.businessDetail && this.businessDetail.address) {
        // 通知父组件
        //复制对象，清空当前对象
        let newObj = {}
        Object.assign(newObj, this.businessDetail)

        this.$emit('location', newObj)

        //清空以前的值
        this.businessDetail = {
          address: undefined,
          latitude: undefined,
          longitude: undefined
        }
        this.keyword = undefined

      } else {
        this.$message.error('请先选择所在位置')
      }
    },
    //搜索完成
    searchcomplete(e) {

      //百度地图类
      let BMapGL = this.BMap
      //地图对象
      let map = this.map
      let that = this

      this.$nextTick(() => {
        if (e && e.Kr && e.Kr[0]) {
          console.log(e)
          this.mapMarkers = e.Kr.slice(0, 4)
          this.center = e.Kr[0].point  //这里获取了检索的位置并跳转
          //清除地图上所有的覆盖物(保证每次点击只有一个标记)
          map.clearOverlays()
          for (let i in this.mapMarkers) {
            let item = this.mapMarkers[i]
            let point = item.point

            //创建定位标记  https://lbsyun.baidu.com/jsdemo.htm#eAddMarker
            let marker = new BMapGL.Marker(new BMapGL.Point(point.lng, point.lat))

            //https://lbsyun.baidu.com/jsdemo.htm#webgl1_4
            //创建消息窗体
            let opts = {
              width: 200,     // 信息窗口宽度
              height: 100,     // 信息窗口高度
              title: item.title || this.businessDetail.address, // 信息窗口标题
              message: item.address || ''
            }
            let infoWindow = new BMapGL.InfoWindow(item.address, opts)  // 创建信息窗口对象

            marker.addEventListener('click', function() {
              that.mapMarkerClick(i, item)
              map.openInfoWindow(infoWindow, point) //开启信息窗口
            })
            //将标记添加到地图上
            map.addOverlay(marker)

          }
          that.zoom = 13

        }
      })
    },
    mapMarkerClick(index, marker) {
      this.businessDetail.address = marker.address
      this.businessDetail.latitude = marker.point.lat
      this.businessDetail.longitude = marker.point.lng
    }
  }

}
</script>

<style scoped>
.bm-view {
  width: 100%;
  height: 300px;
}

.rt {
  text-align: right;
  padding-top: 20px;
}
</style>
