# 📊 Test Execution Summary

| Test Case Name                     | Status  | Details                                                                                          |
|:----------------------------------|:---------|:-------------------------------------------------------------------------------------------------|
| **Login with Valid Credentials**  | ✅ Passed | CAPTCHA handled manually, successful login alert appeared, page title validated as *SmartUniversity*. |
| **Login with Invalid Credentials**| ✅ Passed | Entered invalid credentials, captured and validated inline error message *"Username is not correct"*. |

---

## 📈 Execution Stats

- **Total Test Cases Executed:** 2  
- **Passed:** 2  
- **Failed:** 0  
- **Skipped:** 0  

---

## 📌 Notes

- CAPTCHA handled manually during both executions.
- Invalid login scenario validated against inline error message under the username field.
- Locator for error message was updated based on actual DOM inspection.
- No defects observed in the tested module.
