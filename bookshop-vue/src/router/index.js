import Vue from 'vue'
import VueRouter from 'vue-router'
import AdminLogin from "../admin/AdminLogin";
import Layout from '../admin/Layout';
import Category from '../admin/Category'
import Product from "../admin/Product";
import User from "../admin/User";
import Order from "../admin/Order";
import Home from  "../fore/Home"
import Register from "../fore/Register";
import Login from "../fore/Login"
import CategoryPage from '../fore/CategoryPage'
import Container from "../fore/Container";
import ProductPage from "../fore/ProductPage";
import SearchPage from "../fore/SearchPage";
import Confirm from "../fore/Confirm";
import Cart from "../fore/Cart";
import Pay from "../fore/Pay";

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name:'前台',
    component:Container,
    children:[
      {
        path:'/',
        name: '前台首页',
        component: Home
      },
      {
        path:'/category',
        name:'分类页',
        component:CategoryPage
      },
      {
        path:'/product',
        name:'产品页',
        component:ProductPage
      },
      {
        path:'/search',
        name:'搜索结果页',
        component:SearchPage,
      },
    ]
  },
  {
    path:'/login',
    name:'前台登录',
    component:Login
  },
  {
    path:'/register',
    name:'前台注册',
    component:Register
  },
  {
    path:'/confirm',
    name:'订单结算页',
    component:Confirm,
  },
  {
    path:'/cart',
    name:'购物车页面',
    component:Cart,
  },
  {
    path:'/pay',
    name:'支付页面',
    component:Pay,
  },
  {
    path:'/admin/login',
    name:'admin',
    component:AdminLogin
  },
  {
    path: '/admin',
    name: '后台',
    component: Layout,
    children: [
      {
        path: '/admin',
        name: '分类管理',
        component: Category
      },
      {
        path: '/admin/product',
        name: '商品管理',
        component: Product
      },
      {
        path: '/admin/order',
        name: '订单管理',
        component: Order
      },
      {
        path: '/admin/user',
        name: '用户管理',
        component: User
      },
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../admin/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
