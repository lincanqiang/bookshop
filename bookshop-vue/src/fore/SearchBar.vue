<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-form :inline="true">
                <el-form-item @click.native="$router.push('/')">
                   <img src="../assets/logo.png" width="40px" height="40px"/>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="keyword"
                              placeholder="请输入关键字"
                              size="large"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"
                               @click="handleSearch(keyword)">搜索</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "SearchBar",
        data(){
            return{
                keyword:'',
            }
        },
        methods:{
            handleSearch(keyword){
                this.$router.push({
                    path: '/search',
                    query:{
                        keyword:keyword
                    }
                })
                this.keyword = '';
            }
        }
    }
</script>

<style scoped>

</style>