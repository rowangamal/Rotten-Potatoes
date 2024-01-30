import { createStore } from 'vuex'
export default createStore({
    state:{
        currUser: null,
        currMov: null,
        loginStatus: false,
        forgetUser: null,
        searchMovs:[],
        first:true,
    },
    mutations:{
        setFirst(state,status){
            state.first=status;
        },
        setsearchMovs(state,status){
            state.searchMovs=status;
        },
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