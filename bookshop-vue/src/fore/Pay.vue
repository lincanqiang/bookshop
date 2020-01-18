<template>
    <div>
        <el-row style="text-align: center">
            <div>
                <h3>扫码支付</h3>
                <h1 style="color: #fb4e44">￥{{total}}</h1>
                <img src="../assets/pay.png">
            </div>
            <el-button type="primary" @click="handlePay">确认支付</el-button>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Pay",
        data(){
            return{
                oid:'',
                total:'',
            }
        },
        created() {
            this.oid = this.$route.query.oid;
            this.total = this.$route.query.total;
    },
        methods:{
            handlePay(){
                let url = '/payed' + "?oid=" + this.oid;
                this.axios.get(url).then(response=>{
                    if (200 == response.data.code){
                        this.$message('支付成功');
                        this.$router.push('/');
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>