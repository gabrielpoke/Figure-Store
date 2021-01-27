import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Stock from '@/components/Stock'
import StockHistory from '@/components/StockHistory'
import ActionFigure from '@/components/ActionFigure'
import Manufacturer from '@/components/Manufacturer'
import Category from '@/components/Category'
import Material from '@/components/Material'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard
    },
    {
      path: '/stocks',
      name: 'Stock',
      component: Stock
    },
    {
      path: '/stock-histories',
      name: 'StockHistory',
      component: StockHistory
    },
    {
      path: '/products',
      name: 'ActionFigure',
      component: ActionFigure
    },
    {
      path: '/manufacturers',
      name: 'Manufacturer',
      component: Manufacturer
    },
    {
      path: '/categories',
      name: 'Category',
      component: Category
    },
    {
      path: '/materials',
      name: 'Material',
      component: Material
    }
  ]
})
