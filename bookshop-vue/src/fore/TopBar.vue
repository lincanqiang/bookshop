<template>
    <el-row style="margin-bottom: 20px">
        <div>
        <el-col :span="24"
                style="background: #545E6A">
            <div style="text-align: right">
                <el-dropdown v-if="loginStatus"
                             trigger="click"
                             style="margin-right: 20px">
                    <span style="color: #409EFF">{{username}}的账号</span>
                    <el-dropdown-menu>
                        <el-dropdown-item @click.native="handleOrder">我的订单</el-dropdown-item>
                        <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <el-button type="text"
                           @click="handleSkip"
                           v-else>登录/注册</el-button>
                <el-button type="text">个人中心</el-button>
                <el-button type="text">企业采购</el-button>
                <el-button type="text">客户服务</el-button>
                <el-button type="text">App下载</el-button>
            </div>
        </el-col>
        </div>
    </el-row>
</template>

<script>
    export default {
        name: "NavBar",
        data(){
            return{
                loginStatus: sessionStorage.getItem('user'),
                username:'',
            }
        },
        created() {
            this.username = sessionStorage.getItem('user');
        },
        methods: {
            handleSkip(){
                this.$router.push('/login');
            },
            logout() {
                this.$confirm('确认退出?',{})
                    .then(() =>{
                        sessionStorage.removeItem('user');
                        this.$router.go(0);
                    })
                    .catch(() => {});
            }
        }
    }
</script>

<style scoped>

</style>