<template>
  <div id="login">
    <h1>Login</h1>
    <div>
      <label>
        <input type="text" name="email" v-model="input.email" placeholder="Username"/>
      </label>
    </div>
    <div>
      <label>
        <input type="password" name="password" v-model="input.password" style="margin-top:5px " placeholder="Password"/>
      </label>
    </div>
    <div>
      <button type="button" v-on:click="login()" style="margin-top:10px">Login</button>
      <label>
        <select name="type" v-model="input.userType" size="Number_of_options" style="margin-left:10px">
          <option disabled value="">Please enter the user type</option>
          <option>ADVISOR</option>
          <option>DBR</option>
          <option>GSES</option>
          <option>IBDR</option>
          <option>JURY</option>
          <option>STUDENT</option>
        </select>
      </label>
    </div>
    <div class="response">{{ this.response }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Login',
  data() {
    return {
      input: {
        email: "",
        password: "",
        userType: "",
        id: ""
      },
      response: ''
    }
  },
  methods: {
    login() {
      this.input.userType = this.input.userType.toUpperCase();
      axios({
        method: "POST",
        "url": "http://localhost:8080/mbs/login",
        "data": this.input,
        "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            if (result.data.user != null) {
              this.$emit("authenticated", true);
              this.$store.state.user = result.data.user;
              this.$store.state.authenticated = result.data.success;
              this.$router.replace({name: (result.data.user.userType).toLowerCase() + "home"});
            }
          })
          .catch(error => {
                if (error.response.status === 400) {
                  console.log(error);
                  this.response = `Fields cannot be blank.`
                } else {
                  console.log(error);
                  this.response = `${error.response.data.errorMessage}`
                }
              }
          );
    }
  }
}
</script>

<style scoped>
#login {
  width: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin: 200px auto auto;
  padding: 20px;
}

.response {
  margin-top: 10px;
  color: red;
}
</style>