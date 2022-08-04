<template>
    <view>
        <!--        <uni-search-bar
            @confirm="search"
            clearButton="auto"
            maxlength="20"
            v-model="searchValue"
            @input="input"
            placeholder="搜索商品"
        ></uni-search-bar> -->
        <!-- <uni-load-more status="more" iconType="snow"></uni-load-more> -->
        <uni-list>
            <uni-list-item v-if="this.meituan === undefined" title="这里什么都没有"></uni-list-item>
            <uni-list-item
                v-for="item in meituan"
                :key="item.id"
                :title="item.title"
                :note="item.address"
                :thumb="item.img"
                thumb-size="lg"
                :rightText="item.location"
            ></uni-list-item>
        </uni-list>
        <view class="navigator">
            <uni-row class="nav-row">
                <uni-col :span="5">
                    <navigator url="/pages/index/index" hover-class="navigator-hover"><button type="default">商品</button></navigator>
                </uni-col>
                <uni-col :span="5">
                    <navigator url="navigate/navigate?title=navigate" hover-class="navigator-hover"><button type="default">商家</button></navigator>
                </uni-col>
                <uni-col :span="4">
                    <navigator url="navigate/navigate?title=navigate" hover-class="navigator-hover"><button type="default">+</button></navigator>
                </uni-col>
                <uni-col :span="5">
                    <navigator url="/pages/contamer/index" hover-class="navigator-hover"><button type="default">会员</button></navigator>
                </uni-col>
                <uni-col :span="5">
                    <navigator url="/pages/employee/index" hover-class="navigator-hover"><button type="default">后台</button></navigator>
                </uni-col>
            </uni-row>
        </view>
    </view>
</template>

<script>
import { merchandiseData } from '../../api/index.js';
export default {
    data() {
        return {
            searchValue: '',
            meituan: merchandiseData()
        };
    },
    methods: {
        search() {
            console.log(this.searchValue);
        }
    }
};
</script>

<style>
.container {
    padding: 20px;
    font-size: 14px;
    line-height: 24px;
}
.navigator {
    width: 100%;
    top: 93.2%;
    position: absolute;
}
.navigator,
.nav-row button {
    color: white;
    border: none;
    font-weight: 600;
    background-color: dodgerblue;
}
</style>
