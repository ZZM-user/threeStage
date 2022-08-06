<template>
    <view>
        <uni-list>
            <uni-list-item v-if="this.contamers === undefined" title="这里什么都没有"></uni-list-item>
            <uni-list-item
                v-for="item in contamers"
                :key="item.id"
                :title="item.name"
                :note="item.desc"
                :thumb="item.avatar"
                thumb-size="lg"
                :rightText="item.status"
            ></uni-list-item>
        </uni-list>
    </view>
</template>

<script>
import { contamerData } from '../../api/contamer.js';
export default {
    data() {
        return {
            contamers: contamerData()
        };
    },
    methods: {}
};
</script>

<style scoped></style>
