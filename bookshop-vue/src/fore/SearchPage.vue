<template>
    <div>
        <div>
            <el-alert
                    v-if="beans == ''"
                    title="没有相关结果哦，换个关键字如何？"
                    type="error"
            >
            </el-alert>
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
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "productPage",
        data(){
            return{
                uri:'search',
                beans:[],
            }
        },
        created() {
            this.refresh();
        },
        watch: {
            '$route'() {
                this.refresh();
            }
        },
        methods: {
            refresh() {
                let keyword = this.$route.query.keyword;
                let url = this.uri + "?keyword=" + keyword;
                this.axios.post(url)
                    .then(response => {
                        this.beans = response.data;
                    })
            },
            handleSkip(id) {
                this.$router.push({
                    path: '/product',
                    query: {
                        id: id
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>