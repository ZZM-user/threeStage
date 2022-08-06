<template>
  <div>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商家ID">
        <el-input v-model="queryFrom.eid" placeholder="商家ID"></el-input>
      </el-form-item>
      <el-form-item label="商品名">
        <el-input v-model="queryFrom.name" placeholder="商品名"></el-input>
      </el-form-item>
      <el-form-item label="是否上架">
        <el-select v-model="queryFrom.isgrounding" placeholder="是否上架">
          <el-option
            v-for="item in merchandiseStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="totalData.records" style="width: 100%">
      <el-table-column align="center" label="编号" prop="id" width="80"></el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="名称" prop="name" width="120"></el-table-column>
      <el-table-column label="图片" prop="picture" width="100">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.picture"
            fit="fill"
            style="height: 100px"
          ></el-image>
        </template>

      </el-table-column>
      <el-table-column label="价格" prop="price" width="80">
        <template slot-scope="scope">
          ￥{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="描述" prop="description" width="100"></el-table-column>
      <el-table-column label="状态" prop="isgrounding" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isgrounding===0" effect="dark" type="danger">未上架</el-tag>
          <el-tag v-if="scope.row.isgrounding===1" effect="dark" type="susses">已上架</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="所属商家" prop="b_id" width="60"></el-table-column>
      <el-table-column label="所属分类" prop="m_id" width="60"></el-table-column>
      <el-table-column label="创建时间" prop="create_time"></el-table-column>
      <el-table-column label="创建人" prop="create_by"></el-table-column>
      <el-table-column label="修改时间" prop="update_time"></el-table-column>
      <el-table-column label="修改人" prop="update_by"></el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="queryFrom.page"
      :page-size="queryFrom.size"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="this.fetchData"
    >
    </el-pagination>

  </div>
</template>

<script>
import { fetchMerchandiseData, MerchandiseStatus } from '@/api/merchandise'
import PageMixin from '@/mixin/PageMixin'

export default {
  mixins: [PageMixin],
  data() {
    return {
      merchandiseStatus: MerchandiseStatus()
    }
  }, methods: {
    fetchDataHook() {
      return fetchMerchandiseData
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
}
</style>
