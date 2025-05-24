// Code from -> https://github.com/mrbrunelli/open-closed-principle
import { log } from 'console'

interface Remunerable {
  remuneration: () => number
}

class Senior implements Remunerable {
  remuneration (): number {
    return 7000
  }
}

class Trainee implements Remunerable {
  remuneration (): number {
    return 600
  }
}

class Payroll {
  protected balance: number = 0

  calculate (employe: Remunerable) {
    this.balance = employe.remuneration()
    return this.balance
  }
}

const senior = new Senior()
const trainee = new Trainee()
const payroll = new Payroll()

log('Senior: ' + payroll.calculate(senior))
log('Trainee: ' + payroll.calculate(trainee))
