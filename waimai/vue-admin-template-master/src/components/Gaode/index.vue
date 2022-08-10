<template>
  <div id="GaodeMap">
    <div id="container"></div>
    <div class="input-item">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="商家地址">
          <el-input id="searchtips" v-model="queryValue" placeholder="请输入商家地址" style="width: 500px" type="text"
          />
        </el-form-item>
      </el-form>

    </div>
  </div>

</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader'
import { map } from 'core-js/internals/array-iteration'

export default {
  name: 'GaodeMap',
  data() {
    return {
      map: null,
      // 搜索
      queryValue: '',
      queryResult: [],
      // 定位
      // geolocation: null,
      getGeolocation: '',
      //当前覆盖物的经纬度
      currentLocation: [106.561462, 29.56],
      currentAddress: ''
    }
  },
  mounted() {
    // 提供秘钥
    window._AMapSecurityConfig = {
      securityJsCode: '9c804a2f89b0bd896106a14348312f90'
    }
    //DOM初始化完成进行地图初始化
    AMapLoader.load({
      key: '9a4dc937311b236c9f6823ef4e46d5ee',             // 申请好的Web端开发者Key，首次调用 load 时必填
      version: '2.0',      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
      plugins: ['AMap.ElasticMarker', 'AMap.PlaceSearch', 'AMap.Geolocation', 'AMap.AutoComplete', 'AMap.PlaceSearch', 'AMap.HawkEye', 'AMap.ToolBar', 'AMap.Scale', 'AMap.HawkEye', 'AMap.MapType']        // 需要使用的的插件列表，如比例尺'AMap.Scale'等
    }).then((AMap) => {
      // ------------------------------地图信息-----------------------------------------
      this.map = new AMap.Map('container', {  //设置地图容器id
        viewMode: '3D',    //是否为3D地图模式
        zoom: 13,           //初始化地图级别
        center: this.currentLocation, //初始化地图中心点位置
        mapStyle: 'amap://styles/normal', //地图样式
        features: ['bg', 'point', 'road', 'building'], // 展示元素
        showBuildingBlock: true, //设置地图显示3D楼块效果，移动端也可使用。推荐使用。
        resizeEnable: true,
        isHotspot: true
      })
      const map = this.map
      let that = this

      // ---------------------------插件部分---------------------------------------------------------------
      AMap.plugin(['AMap.BrowserUtil', 'AMap.InfoWindow', 'AMap.Marker', 'AMap.Geolocation', 'AMap.AutoComplete', 'AMap.PlaceSearch', 'AMap.HawkEye', 'AMap.ToolBar', 'AMap.Scale', 'AMap.MapType'], function() {//异步同时加载多个插件
        // 在图面添加工具条控件，工具条控件集成了缩放、平移、定位等功能按钮在内的组合控件
        map.addControl(new AMap.ToolBar({ position: 'LT', offset: [10, 100] }))
        // 在图面添加比例尺控件，展示地图在当前层级和纬度下的比例尺
        map.addControl(new AMap.Scale())

// ----------------------------marker-------------------------------------
        // 点标记
        const refreshForCenter = function() {
          const { lat, lng } = map.getCenter()
          console.log(lat, lng)
          if (that.currentLocation[1] === lat && that.currentLocation[0] === lng) {
            return
          }
          that.currentLocation = [lat, lng]
          const maker = new AMap.Marker({
            position: new AMap.LngLat(lng, lat),
            icon: 'https://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
            anchor: 'bottom-center'
          })
          map.clearMap()
          map.clearInfoWindow()
          map.add(maker)
        }
        refreshForCenter()
        // 给我过来
        const refreshLocation = function(e) {
          const { lng, lat } = e.lnglat
          that.currentLocation = [lat, lng]

          console.log('您在[ ' + lng + ',' + lat + ' ]的位置点击了地图！')
          // 点标记
          const maker = new AMap.Marker({
            position: new AMap.LngLat(lng, lat),
            icon: 'https://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
            anchor: 'bottom-center'
          })
          // 将点击的点设置为地图中心
          map.setCenter([lng, lat])
          // 先清除全部的覆盖物再加
          map.clearMap()
          map.add(maker)
        }
        // 绑定事件
        map.on('click', refreshLocation)

        // --------------------------搜索、候选-------------------------------
        const autoComplete = new AMap.Autocomplete({ city: '全国', input: 'searchtips' })
        autoComplete.on('select', function(e) {
          if (e.poi.district) {
            that.currentAddress = e.poi.district
          }
          if (e.poi.name) {
            that.currentAddress += e.poi.name
          }
          //选择 搜索候选项 就跳转到指定位置
          if (e.poi.location) {
            // 转移地图中心点
            const { lng, lat } = e.poi.location
            map.setCenter([lng, lat])
            that.currentLocation = [lng, lat]
            const maker = new AMap.Marker({
              position: new AMap.LngLat(lng, lat),
              icon: 'https://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
              anchor: 'bottom-center'
            })
            // 将点击的点设置为地图中心
            map.setCenter([lng, lat])
            // 先清除全部的覆盖物再加
            map.clearMap()
            map.add(maker)
          }
        })
        // -----------------------------定位插件-----------------------------
        // 定位插件
        const geolocation = new AMap.Geolocation({
          enableHighAccuracy: true,//是否使用高精度定位，默认:true
          timeout: 10000,          //超过10秒后停止定位，默认：无穷大
          maximumAge: 0,           //定位结果缓存0毫秒，默认：0
          convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
          showButton: true,        //显示定位按钮，默认：true
          buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
          buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
          showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
          showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
          panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
          zoomToAccuracy: true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        })
        map.addControl(geolocation)
        // geolocation.getCurrentPosition() //高精度定位 edge可用
        geolocation.getCityInfo()  //定位到城市
      })
      // --------------------------热点-------------------------------------
      const placeSearch = new AMap.PlaceSearch()  //构造地点查询类
      const infoWindow = new AMap.InfoWindow({})
      map.on('hotspotover', function(result) {
        placeSearch.getDetails(result.id, function(status, result) {
          if (status === 'complete' && result.info === 'OK') {
            placeSearch_CallBack(result)
          }
        })
      })

      //回调函数
      function placeSearch_CallBack(data) {
        const poiArr = data.poiList.pois
        if (poiArr[0].location !== undefined) {
          const location = poiArr[0].location
          infoWindow.setContent(createContent(poiArr[0]))
          infoWindow.open(map, location)
        }
      }

      function createContent(poi) {  //信息窗体内容
        const s = []
        s.push('<div class="info-title">' + poi.name + '</div><div class="info-content">' + '地址：' + poi.address)
        s.push('电话：' + poi.tel)
        s.push('类型：' + poi.type)
        s.push('<div>')
        return s.join('<br>')
      }

      // -----------------------------到此为止-----------------------------------
    }).catch(e => {
      console.log(e)
    })

  },
  methods: {},
  watch: {
    currentLocation() {
      let address = this.queryValue
      if (this.currentAddress !== '') {
        address = this.currentAddress
      }
      this.$emit('change-location', { location: this.currentLocation, address })
      console.log('当前位置' + '-' + this.currentLocation)
    }
  }
}
</script>

<style scoped>

#container {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 300px;
  border-radius: 3%;
}

.demo-form-inline {
  padding: 1% 0;
}

.info-title {
  font-weight: bolder;
  color: #000;
  font-size: 14px;
  width: 250px;
  line-height: 26px;
  padding: 0 0 0 6px;
}

.info-content {
  width: 250px;
  padding: 4px;
  color: #666666;
  line-height: 23px;
  font: 12px Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial;
}

.info-content img {
  float: left;
  margin: 3px;
}

.amap-info-combo .keyword-input {
  height: auto;
}
</style>
