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

<style scoped></style>
