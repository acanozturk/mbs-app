<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorJuryRecommendation">
      <div>
        <h1>Jury Recommendation </h1>
      </div>
      <div>
        <button type="button" v-on:click="selectStudent()" style="margin-top:10px">Select Student</button>
      </div>
      <label>
        <select name="student" v-model="selectedstudent" size="Number_of_options">
          <option v-for="(student, keyStudent) in students" :value="student" :key="keyStudent">{{ student.firstName }}
            {{ student.lastName }}
          </option>
        </select>
      </label>
      <div>
        <p>
          Student: {{ this.student.firstName }} {{ this.student.lastName }}
        <p>
          Thesis Topic : {{ this.student.thesisTopic }}
        </p>
      </div>
      <label>
        <select multiple name="juryMember" v-model="selectedjuryMembers" size="5"
                style="margin-top:50px; margin-left:500px">
          <option v-for="(juryMember, keyjuryMember) in juryMembers" :value="juryMember" :key="keyjuryMember">
            {{ juryMember.firstName }} {{ juryMember.lastName }}
          </option>
        </select>
      </label>
      <button type="button" v-on:click="recommend()" style="margin-top:10px; margin-left:500px">Recommend</button>
      <div>
        {{ this.response }}
      </div>
    </div>
  </div>
</template>

<script>
import LeftMenuAdvisor from './AdvisorLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuAdvisor},
  name: 'AdvisorJuryRecommendation',
  data() {
    return {
      students: [],
      selectedstudent: null,
      juryMembers: [],
      selectedjuryMembers: [],
      student: {
        firstName: "",
        lastName: "",
        thesisTopic: "",

      },
      input: {
        juryMemberIds: [],
        studentId: "",
      },
      response: "",
    };
  },
  mounted() {
    console.log("User ID: " + this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/advisors/getApprovedStudents/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data.studentList);
      if (result.data.studentList != null) {
        this.students = result.data.studentList
      }
    }, error => {
      this.response = `${error.response.data.errorMessage}`
      console.error(error.response);
    });
    axios({method: "GET", "url": "http://localhost:8080/mbs/jurymembers"}).then(result => {
      console.log(result.data.juryMemberList);
      this.juryMembers = result.data.juryMemberList
    }, error => {
      console.error(error);
    });
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    selectStudent() {
      this.clearError();
      this.student.firstName = this.selectedstudent.firstName;
      this.student.lastName = this.selectedstudent.lastName;
      this.input.studentId = this.selectedstudent.id;
      if (this.selectedstudent.thesisTopic != null) {
        this.student.thesisTopic = this.selectedstudent.thesisTopic;
      } else {
        this.input.thesisTopic = "";
      }
    },
    recommend() {
      this.clearError();
      console.log(this.selectedjuryMembers);
      this.selectedjuryMembers.forEach(element => {
        this.input.juryMemberIds.push(element.id);
      });
      console.log(this.input);
      axios({
        method: "POST",
        "url": "http://localhost:8080/mbs/advisors/setTssJury",
        "data": this.input,
        "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
          }).catch(error => {
            console.log(error.response)
        this.response = `${error.response.data.errorMessage}`
          }
      );
      this.input.juryMemberIds = [];
    }
  }
}
</script>

<style scoped>
#AdvisorJuryRecommendation {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>