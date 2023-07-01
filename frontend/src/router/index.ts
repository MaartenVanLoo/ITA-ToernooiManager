// Composables
import { createRouter, createWebHistory } from 'vue-router';
import {useAuthStore} from "@/stores/AuthStore";
import Default from "@/layouts/Default.vue";
import WeightLayout from "@/layouts/WeightLayout.vue";
const Home = () =>import('@/views/Home.vue')
const Login = () =>import('@/views/Auth/Login.vue')
const CompetitionClock = ()=> import("@/views/Tatami/CompetitionClock.vue");
const Info = ()=> import("@/views/Info/Info.vue")
const Weight = ()=>import("@/views/Weight/Weight.vue")
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
  },
  {
    path:'/tatami/:id',
    name: "CompetitionClock",
    component: CompetitionClock
  },
  {
    path:'/info/',
    name: "Info",
    component: Info
  },
  {
    path:'/weight/',
    name: "Weight",
    component: WeightLayout,
    children: [
      {
        //Note: this path is actually unreachable due to redirect /home
        path: '',
        component: Weight,
      }
      ]
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
