import { createStore } from 'vuex'
export default createStore({
    state:{
        currUser: null,
        currMov: null,
        loginStatus: false,
        forgetUser: null,
    },
    mutations:{
        setCurrUser(state, user){
            state.currUser = user;
        },
        setCurrMov(state, mov){
            state.currMov = mov;
        },
        setLoginStatus(state, status){
            state.loginStatus = status;
        },
        setForgetUser(state, user){
            state.forgetUser = user;
        }
    },
    actions:{},
    modules:{}
})