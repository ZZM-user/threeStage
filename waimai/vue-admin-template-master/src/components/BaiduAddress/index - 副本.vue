<template>
  <div>

      <el-form ref="baiForm" :model="businessDetail" label-width="110px">
        <el-form-item label="详细地址：" prop="address">
          <el-autocomplete v-model="businessDetail.address" style="width:100%;" popper-class="autoAddressClass"
            :fetch-suggestions="querySearchAsync" :trigger-on-focus="false" placeholder="详细地址" clearable
            @select="handleSelect">
            <template slot-scope="{ item }">
              <i class="el-icon-search fl mgr10" />
              <div style="overflow:hidden;">
                <div class="title">{{ item.title }}</div>
                <span class="address ellipsis">{{ item.address }}</span>
              </div>
            </template>
          </el-autocomplete>
        </el-form-item>
      </el-form>

      <baidu-map class="bm-view" ak="3LgMlTyAIzk2ok2Sn5ZY6NPPmVQjMFch" :center="mapData.center" :zoom="mapData.zoom"
        @ready="mapHandler" @click="getLocation">
        <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
        <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
        <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>
        <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
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

  import { BaiduMap, BmMarker, BmNavigation,BmGeolocation,BmCityList,BmControl,BmAutoComplete,BmMapType} from 'vue-baidu-map'
  export default {
    name: "BMapSelector",
    components: {
      BaiduMap,
      BmNavigation,
      BmGeolocation,
      BmCityList,
      BmMarker,
      BmControl,
      BmAutoComplete,
      BmMapType
    },
    // 公共暴露属性(不要改)
    props: {
      itemData: {
        type: Object,
        default: () => {},
      }
    },
    data() {
      return {
        //商家信息(包含坐标信息)
        businessDetail: {
          address: undefined,
          latitude: undefined,
          longitude: undefined
        },
        //地图数据
        mapData: {
          //中心坐标
          center: {
            lng: 0,
            lat: 0
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
      if(this.businessDetail.address){
        this.businessDetail.address=this.itemData.address
        this.businessDetail.latitude=this.itemData.latitude
        this.businessDetail.longitude=this.itemData.longitude
      } 
    },
    methods: {
      //地图预处理
      async mapHandler({
        BMap,
        map
      }) {
        console.log('======初始化----')
        // if (this.businessId) {
        //   //可以在此处请求接口获取坐标数据
        //   await this.getMallBusinessDetail()
        // }

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
        } else {
          //如果坐标不存在则动态获取当前浏览器坐标（创建的时候）
          let geolocation = new BMap.Geolocation()
          //获取当前的坐标（使用promise 将异步转换为同步）
          await new Promise((resolve) => {
            geolocation.getCurrentPosition((r) => {
              this.mapData.center.lng = this.businessDetail.longitude =r.point.lng
              this.mapData.center.lat = this.businessDetail.latitude = r.point.lat
              resolve()
            })
          })
        }
        //创建定位标记
        let marker = new BMap.Marker(
          new BMap.Point(
            this.businessDetail.longitude,
            this.businessDetail.latitude
          )
        )
        //将标记添加到地图上
        map.addOverlay(marker)
        this.zoom= 13
      },
      //在地图上选择区域
      getLocation(e) {
        console.log(e)
        //设置经度
        this.businessDetail.longitude = e.point.lng
        //设置纬度
        this.businessDetail.latitude = e.point.lat
        //百度地图类
        let BMapGL = this.BMap
        //地图对象
        let map = this.map
        //清除地图上所有的覆盖物(保证每次点击只有一个标记)
        map.clearOverlays()
        //创建定位标记
        let marker = new BMapGL.Marker(new BMapGL.Point(e.point.lng, e.point.lat))
        //将标记添加到地图上
        map.addOverlay(marker)
        //创建坐标解析对象
        let geoc = new BMapGL.Geocoder()
        //解析当前的坐标成地址
        geoc.getLocation(e.point, (rs) => {
          console.log(rs)
          //获取地址对象
          let addressComp = rs.addressComponents
          //拼接出详细地址
          this.businessDetail.address =
            addressComp.province +
            addressComp.city +
            addressComp.district +
            addressComp.street +
            addressComp.streetNumber
        })
      },
      // 地址搜索
      querySearchAsync(str, cb) {
        var options = {
          onSearchComplete: function(res) {
            var s = []
            if (local.getStatus() == BMAP_STATUS_SUCCESS) {
              for (var i = 0; i < res.getCurrentNumPois(); i++) {
                s.push(res.getPoi(i))
              }
              cb(s)
            } else {
              cb(s)
            }
          }
        }
        var local = new BMap.LocalSearch(this.map, options)
        local.search(str)
      },
      // 8-2、选择地址
      handleSelect(item) {
        this.businessDetail.address = item.address + item.title
        //百度地图类
        let BMapGL = this.BMap
        //地图对象
        let map = this.map
        //清除地图上所有的覆盖物(保证每次点击只有一个标记)
        map.clearOverlays()
        //创建定位标记
        let marker = new BMapGL.Marker(new BMapGL.Point(item.point.lng, item.point.lat))
        //将标记添加到地图上
        map.addOverlay(marker)

        this.mapData.center.lng = this.businessDetail.longitude = item.point.lng
        this.mapData.center.lat = this.businessDetail.latitude = item.point.lat
      },
      //确定选择坐标
      chooseLocation() { 
        if(this.businessDetail && this.businessDetail.address){
          // 通知父组件
          this.$emit('myclick',this.businessDetail)
        }else{
            this.$message.error('请先选择所在位置');
        }
      }
    },

  }
</script>

<style scoped>
  .bm-view {
    width: 100%;
    height: 300px;
  }

  .rt{
        text-align: right;
        padding-top: 20px;
  }
</style>
