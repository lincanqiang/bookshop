<template>
    <div>
    <el-row :gutter="40" style="text-align: center">
        <el-col span="6" :offset="6">
            <el-image :src="bean.image"
                      lazy="true"
                      fit="fill"
                      style="height: 368px;width: 260px"/>
        </el-col>
        <el-col span="6">
            <h3>{{bean.name}}</h3>
            <p style="color: #9d9d9d">{{bean.author}}</p>
            <h2 style="color: #fb4e44;">￥{{bean.price}}</h2>
            <el-tag type="success">{{bean.discount}}折</el-tag>
            <p>{{bean.publisher}}</p>
            <el-input-number v-model="num" :min="1" :max="10"/>
            <p>库存：{{bean.stock}}件</p>
            <el-button type="warning" plain @click="handleAdd">加入购物车</el-button>
            <el-button type="danger" @click="handleBuy">立即购买</el-button>
        </el-col>
    </el-row>
    <el-row style="text-align: center;margin-top: 50px" >
        <el-col span="12" :offset="6">
            <el-collapse>
                <el-collapse-item title="评论" style="text-align: left">
                    <template slot="title">
                        <i class="el-icon-copy-document"/>{{bean.reviewCount}}条书友评论
                    </template>
                    <el-card v-for="item in review" :key="item.key" >
                        <el-tag>{{item.user.name}}</el-tag>
                        <p>{{item.content}}</p>
                        <el-tag type="info" style="text-align: right">{{item.createTime | formatDate}}</el-tag>
                    </el-card>
                </el-collapse-item>
            </el-collapse>
        </el-col>
    </el-row>
    </div>
</template>

<script>
    import { formatDate } from '../store/formatDate'
    export default {
        name: "ProductPage",
        filters: {
            formatDate(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm');
            }
        },
        data(){
            return{
                uri:'product',
                bean:[],
                review:[],
                num: 1,
                activeNames: ['1']
            }
        },
        created() {
            let id = this.$route.query.id;
            let url = this.uri + "/" + id;
            this.axios.get(url)
                .then(response => {
                    let result = response.data;
                    this.bean = result.data.product;
                    this.review = result.data.reviews;
                    }
                )
        },
        methods:{
            handleBuy(){
                if (!sessionStorage.getItem('user')){
                    this.$router.push('/login');
                }else{
                    let pid = this.bean.id;
                    let url = "buy?pid=" + pid + "&num=" + this.num;
                    this.axios.get(url)
                        .then(response => {
                            let oiid = response.data;
                            this.$router.push({
                                path: '/confirm',
                                query:{
                                    oiid: oiid
                                }
                            })
                        })

                }
            },
            handleAdd(){
                if (!sessionStorage.getItem('user')){
                    this.$router.push('/login');
                }
                let pid = this.bean.id;
                let url = "addCart?pid=" + pid + "&num=" + this.num;
                this.axios.get(url)
                    .then(response => {
                        let result= response.data;
                        if(200==result.code) {
                            this.$message('已加入购物车');
                        }
                        })
            }
        }
    }
</script>

<style scoped>

</style>