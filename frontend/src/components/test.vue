<template>
  <div class="workflow">
    <div class="workflow-header">
      <h2 class="workflow-title">
        <i class="mdi mdi-tasks"></i> {{ title }}
      </h2>
      <div class="workflow-progress-wrapper">
        <div class="workflow-progress" :style="{ width: progress + '%' }"></div>
        <div class="workflow-steps-wrapper">
          <ul class="workflow-steps">
            <li v-for="step in steps" :key="step.id" class="workflow-step">
              <i :class="['mdi', step.completed ? 'mdi-check-circle' : 'mdi-circle-outline']" :style="{ color: step.completed ? 'green' : 'red' }"></i>
              {{ step.name }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import '@mdi/font/css/materialdesignicons.css';

interface Step {
  id: number;
  name: string;
  completed: boolean;
  weight?: number;
}

export default defineComponent({
  name: 'Workflow',
  props: {
    title: {
      type: String,
      required: true,
    },
    steps: {
      type: Array as () => Step[],
      required: true,
    },
  },
  computed: {
    progress(): number {
      const completedWeight = this.steps.reduce((total, step) => {
        if (step.completed) {
          return total + (step.weight || 1);
        }
        return total;
      }, 0);
      const totalWeight = this.steps.reduce((total, step) => total + (step.weight || 1), 0);
      return Math.round((completedWeight / totalWeight) * 100);
    },
  },
});
</script>

<style scoped>
.workflow {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
}

.workflow-header {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.workflow-title {
  margin: 0;
}

.workflow-progress-wrapper {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

.workflow-progress {
  flex-grow: 1;
  height: 20px;
  background-color: #38c172;
  border-radius: 2px;
  overflow: hidden;
}

.workflow-steps-wrapper {
  flex-shrink: 0;
  flex-basis: auto;
  margin-top: 10px;
}

.workflow-steps {
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
}

.workflow-step {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.workflow-step:not(:last-child) {
  padding-bottom: 5px;
  border-bottom: 1px solid #ddd;
}

.workflow-step i {
  margin-right: 5px;
  font-size: 20px;
}
</style>
