import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '': JSON.parse(window.localStorage.getItem('user' || '[]')).username,
      id: window.localStorage.getItem('user' || '[]') == null ? '': JSON.parse(window.localStorage.getItem('user' || '[]')).id
    },
    adminMenus: []
  },

  mutations: {
    login (state,user){
      state.user = user
      window.localStorage.setItem("user", JSON.stringify(user))
    },
    logout (state) {
      state.user = []
      window.localStorage.removeItem('user')
    },
    initAdminMenu (state, menus) {
      state.adminMenus = menus
    }

  }

})
