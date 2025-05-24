// Code from -> https://github.com/mrbrunelli/open-closed-principle/
import { log } from 'console'

class Senior {
  salary (): number {
    return 7000
  }
}

class Trainee {
  subsidy (): number {
    return 600
  }
}

class Payroll {
  protected balance: number = 0

  calculate (employe: any) {
    if (employe instanceof Senior) {
      this.balance = employe.salary()
    } else if (employe instanceof Trainee) {
      this.balance = employe.subsidy()
    }
    return this.balance
  }
}

const senior = new Senior()
const trainee = new Trainee()
const payroll = new Payroll()

log('Senior: ' + payroll.calculate(senior))
log('Trainee: ' + payroll.calculate(trainee))