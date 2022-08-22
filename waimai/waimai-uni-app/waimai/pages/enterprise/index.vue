<template>
    <view>
        <uni-card is-full :is-shadow="true">{{ showCardTitle }}</uni-card>
        <uni-list>
            <uni-list-item v-if="this.enterprises === undefined" title="这里什么都没有"></uni-list-item>
            <uni-list-item
                v-for="item in enterprises"
                :key="item.id"
                :title="item.title"
                :note="item.address"
                :thumb="item.img"
                thumb-size="lg"
                :rightText="item.location"
                link="baidu.com"
            ></uni-list-item>
        </uni-list>
    </view>
</template>

<script>
import { enterpriseData } from '../../api/enterprise.js';
export default {
    data() {
        return {
            showCardTitle: '快速入驻，立享收益！',
            enterprises: enterpriseData()
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
</style>
