Feature: Open Amazon Kindle And Verify without email/phone number user should not be check out products
  Scenario: Test Amazon Kindle
    Given open amazon site
    When Click on hamburger menu
    When Click kindle menu item
    When click kindle item under kindle reader menu
    When click on buy now
    Then verify user asked for email or mobile number
