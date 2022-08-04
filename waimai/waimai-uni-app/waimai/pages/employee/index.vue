<template>
    <view>
        <uni-list>
            <uni-list-item v-if="this.employees === undefined" title="这里什么都没有"></uni-list-item>
            <uni-list-item
                v-for="item in employees"
                :key="item.id"
                :title="item.name"
                :note="item.desc"
                :thumb="item.avatar"
                thumb-size="lg"
                :rightText="item.status"
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
import { employeeData } from '../../api/employee.js';
export default {
    data() {
        return {
            employees: employeeData()
        };
    },
    methods: {}
};
</script>

<style scoped>
.navigator {
    cursor: pointer;
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
