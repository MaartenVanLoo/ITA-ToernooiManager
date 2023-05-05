// Composables
import { createRouter, createWebHistory } from 'vue-router';
import {useAuthStore} from "@/stores/AuthStore";
import Default from "@/layouts/Default.vue";
const Home = () =>import('@/views/Home.vue')
const Login = () =>import('@/views/Login.vue')
const routes = [
  {
    path: '/',
    redirect:'/home',
    component: Default,
    children: [
      {
        //Note: this path is actually unreachable due to redirect /home
        path: '',
        component: Home,
      },
      {
        path: 'home',
        name: 'Home',
        component: Home,
      }
    ],
  },
  {
    path:'/login',
    name: 'Login',
    component: Login
  }
  //Default page when all other paths don't match. This must remain the final element in this list.
  //{
  //  path:'/:pathMatch(.*)*',
  //  component:DefaultNotFound,
  //  children: [{
  //    path:'',
  //    name:'NotFound',
  //    component: NotFound
  //  }]
  //
  //}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})


router.beforeEach((to, from, next) => {
  const publicPages = ['/login']
  const authRequired = !publicPages.includes(to.path)
  const authStore = useAuthStore()

  // trying to access a restricted page + not logged in
  // redirect to login page
  //TODO: Enable authentication when backend supports it
  if (authRequired && !authStore.isLoggedIn) {
    return next('/login')
  }

  next()
})

export default router
