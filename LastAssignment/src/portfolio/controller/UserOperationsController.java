package portfolio.controller;

import portfolio.model.UserOperations;

/**
 * This interface is aligned with the command design pattern to allow the class {@link
 * PortfolioManagerController} to create a map of operations and their objects so as to be able to
 * dynamically perform multiple operations one after the other in a structured and abstracted
 * manner.
 */
public interface UserOperationsController {
  /**
   * This function takes in the user model of type {@link UserOperations} and calls the appropriate
   * methods in it to perform the functionality implemented in the individual classes this interface
   * is implemented by.
   *
   * @param userModel the model containing all the operations to be executed.
   * @return true if the operation executes completely, and false if the user wants to quit mid-way.
   */
  boolean apply(UserOperations userModel);
}