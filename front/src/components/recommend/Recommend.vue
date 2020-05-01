<template>
    <div>
        <el-backtop :bottom="250" :right="20">
            <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
                <i class="el-icon-upload2"></i>
            </div>
        </el-backtop>

        <Head :index="activeIndex"></Head>

        <div class="recommend">
            <el-carousel height="300px" class="el-carousel">
                <el-carousel-item v-for="(item,index) in carouselList" :key="index">
                    <img style="width: 100%;height: 300px;min-width: 1250px;"
                         v-bind:src="item.img_url"/>
                </el-carousel-item>
            </el-carousel>

            <el-tabs type="border-card" @tab-click="handleClick" v-model="activeTab">
                <el-tab-pane label="猜你喜欢" name="猜你喜欢">
                    <span slot="label"><i class="el-icon-magic-stick"></i> 猜你喜欢</span>
                    <figure v-for="(item,index) in preferList" :key="index" @click="handleCheck(item.id)">
                            <img src="./girl2.jpg"/>
                            <h2>{{item.username}}</h2>
                    </figure>
                </el-tab-pane>

                <el-tab-pane v-for="(item,index) in hottestLabel" :key="index" :label=item.label :name=item.label>
                    <figure v-for="(user,i) in userList" :key="i" @click="handleCheck(user.id)">
                        <img src="./girl.png"/>
                        <h2>{{user.username}}</h2>
                    </figure>
                </el-tab-pane>
            </el-tabs>
        </div>

        <div style="margin-top: 40px">
            <el-card style="width: 1250px; margin: 0 auto; border: none" shadow="false">
                <el-button class="searchButton" plain type="text" @click="handleClicktoSearch">前往搜索页面
                </el-button>
            </el-card>
        </div>

        <div style="margin-top: 50px">
            <el-card shadow="false" style="width: 1600px;margin: 0 auto; border: none">
                <p style="text-align: center">关于我们|联系我们|加入我们|合作伙伴| 意见反馈|安全中心|网站地图 | 帮助中心|精英会员|个人信息保护政策</p>
                <p style="text-align: center">品牌：10年专业婚恋服务 专业：庞大的资深红娘队伍</p>
                <p style="text-align: center">客服热线：4001-520-520（周一至周日：9:00-21:00）客服信箱：1234567890@jingying.com</p>
                <p style="text-align: center">违法和不良信息举报 4001-520-520 举报信箱：1234567891@jingying.com</p>
                <p style="text-align: center">Copyright © 2010-2020 版权所有：西虹市精英婚庆网信息技术有限公司</p>
            </el-card>
        </div>
    </div>
</template>

<script>
    import Head from '../head/Head'
    import axios from 'axios'
    import head_img1 from './sample2.jpg'
    import head_img2 from './sample3.jpg'
    import head_img3 from './sample4.jpg'

    export default {
        name: "Recommend",
        data() {
            return {
                user: 666,
                activeIndex: '2',
                activeTab: '猜你喜欢',
                preferList: {},
                hottestLabel: {},
                userList: {},
                carouselList: [{
                    img_name: 'paris',
                    img_url: head_img1
                }, {
                    img_name: 'church',
                    img_url: head_img2
                }, {
                    img_name: 'jeneva',
                    img_url: head_img3
                }]
            }
        },
        components: {
            Head
        },
        created() {
            this.getData()
            this.getHottestLabel()
        },
        methods: {
            getData() {
                let url = `http://localhost:8999/wedding/user/preferList/${this.user}`
                axios.get(url).then((res) => {
                    // console.log(res)
                    this.preferList = res.data
                    // console.log(url)
                })
            },
            getHottestLabel() {
                let url = 'http://localhost:8999/wedding/labelHeat/heat_list'
                axios.get(url).then((res) => {
                    // console.log(res)
                    this.hottestLabel = res.data
                    // console.log(url)
                })
            },
            handleClick(tab, event) {
                // console.log(event.target.getAttribute('id'))
                if (event.target.getAttribute('id') === "tab-猜你喜欢") {
                    console.log('aa')
                } else {
                    var label = event.target.getAttribute('id').substr(4)

                    let url = `http://localhost:8999/wedding/user/label_search/${label}`
                    axios.get(url).then((res) => {
                        // console.log(res)
                        this.userList = res.data
                        // console.log(url)
                    })
                }
            },
            handleClicktoSearch() {
                this.$router.push({
                    path: '/search'
                })
            },
            handleCheck(id) {
                this.$router.push({
                    path: `/check/${id}`
                })
            },
        }
    }
</script>

<style scoped>
    .el-carousel {
        margin-bottom: 20px;
    }

    .recommend {
        width: 1250px;
        text-align: center;
        margin: 0 auto;
    }

    /*.el-carousel__item h3 {*/
    /*    color: #475669;*/
    /*    font-size: 20px;*/
    /*    opacity: 0.75;*/
    /*    line-height: 150px;*/
    /*    margin : 0 auto;*/
    /*    vertical-align: middle;*/
    /*}*/

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    figure {
        width: 212px;
        float: left;
        padding: 2px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    figure img {
        width: 212px;
        height: 200px;
    }

    figure h2 {
        text-align: center;
        font-size: 22px;
        color: #666;
        white-space: nowrap; /* 不换行*/
        overflow: hidden; /* 超出不显示*/
        text-overflow: ellipsis; /* 加省略号显示*/
    }

    .searchButton {
        background-color: mediumpurple;
        width: 300px;
        height: 45px;
        color: white;
        font-size: 16px;
        margin-left: 40%
    }
</style>
