<template>
    <view>
         <!-- 顶栏 -->
        <view class="box-bg">
        	<uni-nav-bar>
                <block slot="left">
        			<view class="city">
        				<view >
        					<text class="uni-nav-bar-text" v-for="item in citys" :key="item.id">{{ item.name }}</text>
        				</view>
                        <uni-icons type="arrowdown" color="#666" size="18" />
                    </view>
        		</block>
        	<view class="input-view">
        		<uni-icons class="input-uni-icon" type="search" size="18" color="#999" />
        		<input confirm-type="search" class="nav-bar-input" v-model="searchValue"
                    type="text" placeholder="请输入商家名、品类或者商圈" @confirm="confirm" />
        	</view>
        		<block slot="right">
                    <view class="city">搜索</view>
        		</block>
        	</uni-nav-bar>
        </view>
        <!-- 轮播图 -->
        <view class="uni-margin-wrap">
            <swiper class="swiper" circular :indicator-dots="true" :autoplay="true" interval="5000">
                <swiper-item v-for="item in merchandiseImg" :key="item.id">
                    <view>
                        <view class="image-content">
                            <image
                                style="width: 450px;height: 200px; background-color: #eeeeee;"
                                mode="scaleToFill"
                                :src="item.img"
                                @error="imageError"
                            ></image>
                        </view>
                    </view>
                </swiper-item>
            </swiper>
        </view>
        <uni-section class="tenGrid" title="周边优惠" type="line">
            <uni-grid :column="5" :highlight="true" :square="false" :showBorder="false" @change="change">
                <uni-grid-item v-for="(item, index) in merchandiseTenGrid" :index="index" :key="index">
                    <view class="grid-item-box" style="background-color: #fff;text-align: center;">
                        <image class="image" :src="item.icon" mode="aspectFit" style="width: 60px;height: 60px;" />
                        <!-- <uni-icons type="image" customPrefix="item.icon" :size="30" color="#777" /> -->
                        </br><text class="text">{{ item.title }}</text>
                    </view>
                </uni-grid-item>
            </uni-grid>
        </uni-section>
        <uni-section title="猜你喜欢" type="line">
            <uni-list>
                <uni-list-item v-if="this.merchandises === undefined" title="这里什么都没有"></uni-list-item>
                <uni-list-item
                    v-for="item in merchandises"
                    :key="item.id"
                    :title="item.title"
                    :note="item.desc"
                    :thumb="item.img"
                    thumb-size="lg"
                    :rightText="item.price"
                    link="baidu.com"
                ></uni-list-item>
            </uni-list>
        </uni-section>
    </view>
</template>

<script>
import { merchandiseData, merchandiseTenGrid, merchandiseImg,Citys } from '../../api/merchandise.js';
export default {
    data() {
        return {
            searchValue: '',
            citys: Citys(),
            merchandiseImg: merchandiseImg(),
            merchandiseTenGrid: merchandiseTenGrid(),
            merchandises: merchandiseData()
        };
    },
    methods: {
        search() {
            console.log(this.searchValue);
        }
    }
};
</script>

<style lang="scss">
.container {
    padding: 20px;
    font-size: 14px;
    line-height: 24px;
}
$nav-height: 30px;

	.box-bg {
		background-color: #F5F5F5;
		padding: 5px 0;
	}

	.city {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		width: 160rpx;
		margin-left: 4px;
        margin-right: 2px;
	}

	.input-view {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		// width: 500rpx;
		flex: 1;
		background-color: #f8f8f8;
		height: $nav-height;
		border-radius: 15px;
		padding: 0 15px;
		flex-wrap: nowrap;
		margin: 8px 0;
		line-height: $nav-height;
	}

	.input-uni-icon {
		line-height: $nav-height;
	}

	.nav-bar-input {
		height: $nav-height;
		line-height: $nav-height;
		/* #ifdef APP-PLUS-NVUE */
		width: 370rpx;
		/* #endif */
		padding: 0 5px;
		font-size: 12px;
		background-color: #f8f8f8;
	}
</style>
