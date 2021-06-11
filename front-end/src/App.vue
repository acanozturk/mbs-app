<template>
    <div id="app">
        <div id="response">
            {{this.$store.state.user.firstName}}
        </div>
        <div id="nav">
            <router-link v-if="authenticated" to="/login" v-on:click.native="logout()" replace>Logout</router-link>
        </div>
        <router-view @authenticated="setAuthenticated" /> 
    </div>
</template>

<script>

    export default {
        name: 'App',
        data() {
            return {
                //authenticated: true, //emitted from login !!To use left menu change here as true!!
                mockAccount: {
                    username: "mock",
                    password: "mock"
                }
            }
        },
        mounted() {
            if(!this.authenticated) {
                this.$router.replace({ name: "login" });
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
                this.$store.state.user = "";
            }
        },
    }
</script>

<style>
    body {
        background-color: #F0F0F0;
    }
    h1 {
        padding: 0;
        margin-top: 0;
    }
    #app {
        width: 1024px;
    }
    #response {
    text-align: right;
    }
</style>