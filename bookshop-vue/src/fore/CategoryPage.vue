<template>
    <div>
        <div>
        <el-row style="text-align: center;margin-left: 20%;margin-right: 20%"
                :gutter="20">
            <el-col v-for="item in beans"
                    :key="item.id"
                    :span="6"
                    style="margin-top: 20px"
            >
                <el-card :body-style="{padding:'0px'}"
                         style="width: 195px"
                         shadow="hover"
                         @click.native="handleSkip(item.id)">
                    <el-image :src="item.image"
                              style="height: 276px;width: 195px"
                    />
                    <div style="text-align: center">
                        <p style="font-size: 5px">{{item.name}}</p>
                        <p style="color: #9d9d9d;font-size: 3px">{{item.author}}</p>
                        <p style="color:#fb4e44;font-size: 5px">¥{{item.price}}</p>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-pagination
                        background
                        layout="total,prev,pager,next"
                        :page-size="5"
                        :pager-count="5"
                        :hide-on-single-page="value"
                        @current-change="handleCurrentChange"
                        :total="total"
                        :current-page="pageNum"
                />
            </el-col>
        </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "producePage",
        data(){
            return{
                uri:'categories',
                total:0,
                pageNum:0,
                beans:[],
            }
        },
        created() {
            this.list(1);
            },
        methods:{
            list(pageNum) {
                let id = this.$route.query.id
                let url = this.uri + "/" + id +"/products" + "?pageNum=" + pageNum;
                this.axios.get(url)
                    .then(response => {
                        this.beans = response.data.content;
                        this.total = response.data.totalElements;
                    })
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.list(val);
            },
            handleSkip(id){
                this.$router.push({
                    path: '/product',
                    query:{
                        id:id
                    }
                })
            }
        }
    }
</script>

<style scoped>
</style>